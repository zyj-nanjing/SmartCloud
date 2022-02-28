package www.bwsensing.com.domain.device.model.data.model;


import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import www.bwsensing.com.domain.common.math.Calculator;
import www.bwsensing.com.domain.monitor.model.MonitorPrototype;

import java.math.BigInteger;
import java.util.regex.Pattern;


/**
 * @author macos-zyj
 */
@Data
public class DataModelItem implements Comparable<DataModelItem>{
    /**
     * 默认数据进制
     */
    private static Integer DEFAULT_SYSTEM = 10;
    /**
     * 主键
     */
    private Integer id;

    /**监测因素编号 暂时没考虑好先放这边*/
    @Deprecated
    private Integer prototypeId;

    /**
     * 编码类型
     */
    private DataItemKind itemKind;


    /***
     * 唯一编码长度
     */
    private Integer uniqueCodeSize;

    /**
     * 监测因素
     */
    private MonitorPrototype prototype;

    /**
     * 数据位名称
     */
    private String dataName;

    /**
     * 数据位置
     */
    private Integer dataOrder;


    /**
     * 数据长度
     */
    private Integer dataLength;

    /**
     * 正则表达式
     */
    private String dataFormat;

    /**
     * 数据类型 换成枚举
     */
    private DataType dataType;

    /**
     * 是否需要转换
     */
    private Boolean needTransform;

    /**
     * 计算公式
     */
    private String calculationFormula;

    /**
     * 占位符
     */
    private String placeholder;


    public DataModelItem() {

    }

    public DataModelItem(Integer dataOrder, DataItemKind dataItemKind,
                         DataType dataType) {
        this.dataOrder = dataOrder;
        this.itemKind = dataItemKind;
        this.dataType = dataType;
        this.needTransform = false;
    }

    public DataModelItem(Integer dataOrder, DataItemKind dataItemKind,
                         DataType dataType,Integer dataLength) {
        this.dataOrder = dataOrder;
        this.itemKind = dataItemKind;
        this.dataType = dataType;
        this.needTransform = false;
        this.dataLength = dataLength;
    }

    public DataModelItem(Integer uniqueCodeSize, Integer dataOrder,
                         Integer dataLength, String dataFormat,
                         DataType dataType) {
        this.itemKind = DataItemKind.UNIQUE_SN;
        this.uniqueCodeSize = uniqueCodeSize;
        this.dataOrder = dataOrder;
        this.dataLength = dataLength;
        this.dataFormat = dataFormat;
        this.dataType = dataType;
        this.needTransform = false;
    }

    public DataModelItem(MonitorPrototype prototype, Integer dataOrder,
                         Integer dataLength,DataType dataType) {
        this.itemKind = DataItemKind.DATA_INDEX;
        this.prototype = prototype;
        this.dataOrder = dataOrder;
        this.dataLength = dataLength;
        this.dataType = dataType;
        this.needTransform = false;
    }


    public DataModelItem(MonitorPrototype prototype, Integer dataOrder,
                         Integer dataLength, DataType dataType,
                         String calculationFormula, String placeholder) {
        this.itemKind = DataItemKind.DATA_INDEX;
        this.prototype = prototype;
        this.dataOrder = dataOrder;
        this.dataLength = dataLength;
        this.dataType = dataType;
        this.needTransform = true;
        this.calculationFormula = calculationFormula;
        this.placeholder = placeholder;
    }

    public String geyUniqueCode(String splitData){
        return geyUniqueCode(splitData,DEFAULT_SYSTEM);
    }

    public String geyUniqueCode(String splitData,Integer carrySystem) {
        Assert.notNull(dataFormat,"唯一编码数据位校验有误!");
        Assert.notNull(dataType,"数据类型不能为空有误!");
        String resultCode = "";
        if (dataType == DataType.STRING) {
            resultCode = splitData;
        } else {
            int codeSn = mathCalculation(splitData, carrySystem).intValue();
            String snNumbers = Integer.toString(codeSn);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < uniqueCodeSize - snNumbers.length(); i++) {
                result.append("0");
            }
            resultCode = result.append(snNumbers).toString();
        }
        dataFormat = dataFormat.replace("{size}",uniqueCodeSize.toString());
        boolean isMatch = Pattern.matches(dataFormat, resultCode);
        if (isMatch){
            return resultCode;
        } else{
            throw new BizException("unique_code_check_error");
        }
    }

    public Double mathCalculation(String splitData){
        return mathCalculation(splitData, DEFAULT_SYSTEM);
    }

    public Double mathCalculation(String splitData,Integer carrySystem){
        double defaultValue;
        if(DEFAULT_SYSTEM.equals(carrySystem)){
            defaultValue = Double.parseDouble(splitData);
        } else {
            String floatValue = String.valueOf(Float.valueOf(new BigInteger(splitData, carrySystem).intValue()));
            defaultValue = Double.parseDouble(floatValue);
        }
        if (needTransform){
            if(StringUtils.isNotEmpty(calculationFormula)){
                if(!DEFAULT_SYSTEM.equals(carrySystem)){
                    splitData = Double.toString(defaultValue);
                }
                String fixedValue = calculationFormula.replace(placeholder,splitData);
                return Calculator.conversion(fixedValue);
            } else {
                throw new BizException("calculation_formula_not_set","计算公式暂未设置");
            }
        }
        return defaultValue;
    }

    public String getIdentifyCode(String splitData){
        return splitData;
    }

    @Override
    public int compareTo(DataModelItem item) {
        return this.dataOrder - item.getDataOrder();
    }
}
