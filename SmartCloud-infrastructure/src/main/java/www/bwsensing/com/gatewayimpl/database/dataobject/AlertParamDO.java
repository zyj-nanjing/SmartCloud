package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
/**
 * @author macos-zyj
 */
@Data
public class AlertParamDO {
    /**主键*/
    private Integer id;
    /**模板编号*/
    private Integer templateId;
    /**告警名称*/
    private String alertName;
    /**监测参数编号 注数据库编号*/
    private Integer paramNo;
    /**问题发生后的持续时间*/
    private String lastTime;
    /**监测周期*/
    private String period;
    /**
     * 每组格式为 (max,>,10,&&)  第一位为 函数 可以为 max avg min  第二位为 运算符 可以为 < <= >  >= != == 第三位为具体数据 第四位为 逻辑或按位 运算符  || | && & 注意数组末尾不要加加了校验不会让你过的
     **/
    private String formulas;
    /**颜色*/
    private String color;
    /**告警信息*/
    private String summary;
}
