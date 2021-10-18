package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
/**
 * 监测原则
 * @author macos-zyj
 */
@Data
public class AlertRoleDO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 告警名称
     */
    private String alertName;
    /**
     * 告警组编号
     */
    private Integer alertGroupId;
    /**
     * 颜色
     */
    private String color;
    /**
     * 模板编号
     */
    private Integer templateId;
    /**
     * 参数编号
     */
    private Integer paramId;
    /**
     * 小组编号
     */
    private  Integer operateGroupId;
    /**
     * 传感器编号
     */
    private Integer sensorId;
    /**
     * 预警公式
     */
    private String formulas;

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
     * 内涵信息用于解析
     */
    private String alertInfo;
    /**用于为规则指定一个唯一的名字*/
    private String name;
    /**从 TDEngine 中查询数据时使用的 sql 语句*/
    private String stateSql;
    /**未修改回查时间的SQL语句*/
    private String templateSql;
    /**一个计算结果为布尔型的表达式，支持算数运算、逻辑运算，并且内置了部分函数，也可以引用查询结果中的列*/
    private String expr;
    /**当表达式计算结果为 true 的连续时长超过这个选项时，触发报警，否则报警处于“待定”状态。默认为0，表示一旦计算结果为 true，立即触发报警*/
    private String lastTime;
    /**规则的检查周期，默认1分钟。*/
    private String checkPeriod;
}
