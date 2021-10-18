package www.bwsensing.com.domain.device.alert;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import www.bwsensing.com.domain.device.SensorInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * 告警数据模型
 * @author macos-zyj
 */
@Data
public class AlertRole {
    private static final String AUTO_ROLE_NAME = "AUTO_ROLE";
    /**
     * 主键
     */
    private Integer id;
    /**
     * 告警规则分组编号
     */
    private Integer alertGroupId;
    /**
     * 规则编号
     */
    private Integer roleCode;
    /**
     * 模板编号
     */
    private Integer templateId;
    /**
     * 传感器编号
     */
    private Integer sensorId;
    /**
     * 设备信息
     */
    private SensorInfo sensorInfo;
    /**
     * 小组编号
     */
    private  Integer operateGroupId;
    /**
     * 参数模板
     */
    private AlertTemplate alertTemplate;
    /**
     * 参数编号
     */
    private Integer paramId;
    /**
     * 参数项
     */
    private AlertParam alertParam;
    /**
     * 回查时间
     */
    private String forward;
    /**
     * 标签
     */
    private String label;
    /**
     * 提示信息
     */
    private String summary;

    /**
     * 监测状态
     */
    private Boolean alertStatus;

    /**
     * 颜色
     */
    private String color;

    /**
     * 内涵信息用于解析
     */
    private String alertInfo;
    /**
     * 预警公式
     */
    private List<String> formulas;
    /**
     * 告警名称
     */
    private String alertName;

    /**用于为规则指定一个唯一的名字*/
    private String name;
    /**从 TDEngine 中查询数据时使用的 sql 语句*/
    private String stateSql;
    /**未修改回查时间的SQL语句*/
    private String templateSql;
    /**
     * 一个计算结果为布尔型的表达式，支持算数运算、逻辑运算，并且内置了部分函数，也可以引用查询结果中的列
     */
    private String expr;
    /**当表达式计算结果为 true 的连续时长超过这个选项时，触发报警，否则报警处于“待定”状态。默认为0，表示一旦计算结果为 true，立即触发报警*/
    private String lastTime;
    /**规则的检查周期，默认1分钟。*/
    private String checkPeriod;
    /**人为指定的标签列表，标签可以在生成报警信息引用。*/
    private Labels labels;
    /**用于定义报警信息，使用 go template 语法*/
    private Annotations annotations;

    public Map<String, Object> toHttpRestMap(){
        Map<String, Object> httpRestMap = new LinkedHashMap<>(16);
        httpRestMap.put("name",name);
        httpRestMap.put("sql", stateSql);
        httpRestMap.put("expr",expr);
        httpRestMap.put("for", lastTime);
        httpRestMap.put("period", checkPeriod);
        if (null != this.labels){
            httpRestMap.put("labels", labels.toLabels());
        }
        if (null != this.annotations){
            httpRestMap.put("annotations",annotations.toAnnotations());
        }
        return httpRestMap;
    }

    public AlertRole() {

    }

    public AlertRole(SensorInfo sensorInfo, AlertParam alertParam) {
        this(sensorInfo, alertParam,null);
    }

    public AlertRole(SensorInfo sensorInfo, AlertParam alertParam, AlertTemplate template) {
        this.alertParam = alertParam;
        this.alertTemplate = template;
        this.sensorInfo = sensorInfo;
        initRoleLabel(template);
        this.sensorId = sensorInfo.getId();
        this.operateGroupId = sensorInfo.getMemberGroupId();
        this.initSummaryAndAnnotation();
        this.initAlertRoleParam();
        this.templateSql = alertParam.toAlertSql(sensorInfo.getSn());
        this.alertStatus = true;
    }


    public void updateAlertRole(){
        this.stateSql  = this.getTemplateSql().replace("[forward]",this.forward);
        if (null == this.alertParam){
            this.alertParam = new  AlertParam();
        }
        this.alertParam.setFormulas(this.getFormulas());
        this.expr = this.alertParam.toAlertExpr();
        if (null == this.labels){
            this.labels = new  Labels(this.label);
        }
        if (null == this.annotations){
            this.annotations = new  Annotations(this.alertInfo,this.summary);
        }
    }
    public void insertGroupInfo(){
        this.alertInfo = this.alertInfo+"|"+this.alertGroupId;
        this.annotations.setInfo(alertInfo);
    }
    private void initRoleLabel(AlertTemplate template){
        if (null != template){
            this.templateId = template.getId();
            this.initRandom(null);
            this.label = template.getNamePrefix();
        } else{
            this.initRandom(AUTO_ROLE_NAME+sensorInfo.getMemberGroupId());
            this.label = AUTO_ROLE_NAME;
        }
    }

    private void initAlertRoleParam(){
        this.expr = this.alertParam.toAlertExpr();
        this.lastTime = this.alertParam.getLastTime();
        this.checkPeriod = this.alertParam.getPeriod();
        if (null != this.alertParam.getId()){
            this.paramId = this.alertParam.getId();
        }
        this.color = this.alertParam.getColor();
        this.formulas = this.alertParam.getFormulas();
    }

    private void initRandom(String setName){
        int rangeId = getRandomNameId();
        if(StringUtils.isEmpty(setName)){
            this.name = this.alertTemplate.getNamePrefix()+rangeId;
        } else {
            this.name = setName + rangeId;
        }
        this.alertName = this.alertParam.getAlertName()+rangeId;
    }


    private Integer getRandomNameId(){
        int max=10000,min=1;
        long randomNum = System.currentTimeMillis();
        return (int) (randomNum%(max-min)+min);
    }


    private void initSummaryAndAnnotation(){
        this.summary = getRoleSummary();
        Annotations annotations = new Annotations();
        annotations.setSummary(summary);
        String alertInfo = this.sensorInfo.getSn()+"|"+this.operateGroupId +"|"+this.name;
        annotations.setInfo(alertInfo);
        this.alertInfo = alertInfo;
        this.annotations = annotations;
    }


    private String getRoleSummary(){
        SensorInfo sensor = this.sensorInfo;
        return this.alertParam.getSummary().replace("${roleName}",this.alertName)
                .replace("${dataNumber}","{{$values.data_value}}")
                .replace("${sensorModel}",sensor.getModelName())
                .replace("${sensor}",sensor.getName());
    }
}
