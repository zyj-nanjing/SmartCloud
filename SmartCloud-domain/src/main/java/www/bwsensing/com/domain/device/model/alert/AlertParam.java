package www.bwsensing.com.domain.device.model.alert;

import lombok.Data;
import java.util.List;
import java.util.regex.Pattern;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.domain.device.model.alert.formulas.ParamFunction;
import www.bwsensing.com.domain.device.model.alert.formulas.ParamLogic;
import www.bwsensing.com.domain.device.model.alert.formulas.ParamOperator;
import www.bwsensing.com.domain.device.model.ProductDataItem;

/**
 * 告警参数
 * @author macos-zyj
 */
@Data
public class AlertParam {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static final Integer MAX_FORMULA_LENGTH =4;
    private static final String NULL = " ";
    private static final String DATA_NAME = "data_value";
    private static final String ALERT_EXPR_MODEL = "[FUNCTION]([DATA_NAME]) [OPERATOR] [NUMERIC]";
    private static final String ALERT_SQL_MODEL = "select  data_value from smart_cloud.sensor_data where ts > now - [forward] and data_id ='[dataId]' and sn ='[sn]'";
    /**主键*/
    private Integer id;
    /**告警名称**/
    private String alertName;
    /**监测参数编号 注数据库编号*/
    private Integer paramNo;
    /**
     * 检测项
     */
    private ProductDataItem monitorItem;
    /**
     * 每组格式为 (max,>,10,&&)  第一位为 函数 可以为 max avg min  第二位为 运算符 可以为 < <= >  >= != == 第三位为具体数据 第四位为 逻辑或按位 运算符  || | && & 注意数组末尾不要加加了校验不会让你过的
     **/
    private List<String> formulas;
    /**问题发生后的持续时间*/
    private String lastTime;
    /**监测周期*/
    private String period;
    /**颜色*/
    private String color;
    /**告警信息*/
    private String summary;

    public void create(){
        if (null == lastTime || NULL.equals(this.lastTime)){
            this.lastTime = "0";
        }
        if (null == period || NULL.equals(this.period)){
            this.period = "1m";
        }
    }

    public void validate() {
        for (int i = 0;i < formulas.size(); i++){
            String [] formula = formulas.get(i).split(",");
            if(i!=formulas.size()-1){
                Assert.isTrue(formula.length>=MAX_FORMULA_LENGTH);
                ParamLogic.getParamLogic(formula[3]);
            }else {
                Assert.isTrue(formula.length>=3,"运算公式格式有误");
            }
            ParamFunction.getParamFunction(formula[0]);
            ParamOperator.getParamOperator(formula[1]);
            if(!isNumeric(formula[2])){
                throw new BizException("NOT_NUMBER","数据位非数字");
            }
        }
    }


    public boolean isNumeric(String str) {
        return str != null && NUMBER_PATTERN.matcher(str).matches();
    }

    public String toAlertSql(String sn){
        return ALERT_SQL_MODEL.replace("[sn]",sn).replace("[dataId]",monitorItem.getDataId());
    }
    /**
     * 生成预警计算表达式
     * @return
     */
    public String toAlertExpr(){
        StringBuilder exprResult = new StringBuilder();
        validate();
        this.formulas.forEach(currentLine ->{
            String [] formula = currentLine.split(",");
            Assert.isTrue(formula.length>=3,"预警模板公式有误");
            String currentExpr = ALERT_EXPR_MODEL.replace("[FUNCTION]",formula[0])
                    .replace("[DATA_NAME]",DATA_NAME).replace("[OPERATOR]",formula[1])
                    .replace("[NUMERIC]",formula[2]);
            if (formula.length>=MAX_FORMULA_LENGTH){
                currentExpr += " "+formula[3].replace(" ","")+" ";
            }
            exprResult.append(currentExpr);
        });
        return exprResult.toString();
    }
}
