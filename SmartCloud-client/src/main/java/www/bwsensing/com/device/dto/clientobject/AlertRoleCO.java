package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import java.util.List;
/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class AlertRoleCO extends DTO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 预警名称
     */
    private String alertName;
    /**
     * 预警名称
     */
    private String name;
    /**
     * 模板编号
     */
    private Integer templateId;
    /**
     * 参数编号
     */
    private Integer paramId;

    /**
     * 预警公式
     */
    private List<String> formulas;

    /**
     * 回查时间
     */
    private String forward;
    /**
     * 标签
     */
    private String label;

    private String color;

    /**
     * 提示信息
     */
    private String summary;
    /**
     * 监测状态
     */
    private Boolean alertStatus;
    /**一个计算结果为布尔型的表达式，支持算数运算、逻辑运算，并且内置了部分函数，也可以引用查询结果中的列*/
    private String expr;
    /**当表达式计算结果为 true 的连续时长超过这个选项时，触发报警，否则报警处于“待定”状态。默认为0，表示一旦计算结果为 true，立即触发报警*/
    private String lastTime;
    /**规则的检查周期，默认1分钟。*/
    private String checkPeriod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlertName() {
        return alertName;
    }

    public void setAlertName(String alertName) {
        this.alertName = alertName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public List<String> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<String> formulas) {
        this.formulas = formulas;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getAlertStatus() {
        return alertStatus;
    }

    public void setAlertStatus(Boolean alertStatus) {
        this.alertStatus = alertStatus;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getCheckPeriod() {
        return checkPeriod;
    }

    public void setCheckPeriod(String checkPeriod) {
        this.checkPeriod = checkPeriod;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
