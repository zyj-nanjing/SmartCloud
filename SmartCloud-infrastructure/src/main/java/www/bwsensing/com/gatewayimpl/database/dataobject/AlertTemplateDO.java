package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class AlertTemplateDO {
    /**主键*/
    private Integer id;
    /**模型编号 告警模板对应的设备型号*/
    private Integer modelNo;
    /**模型名称*/
    private String modelName;
    /**业务组编号*/
    private Integer groupId;
    /**模型名称*/
    private String templateName;
    /**告警role前缀名称 唯一会校验唯一性*/
    private String namePrefix;
    /**监测参数*/
    private List<AlertParamDO> alertParams;
    /**
     * 告警信息模板   支持占位符 ${item} 检测项名称  ${paramName} 当前数值   ${sensor} 传感器名称  ${alertName} 对应监测相位告警信息 ${paramName}
     * 类似格式 传感器:${sensor} 发生 ${alertName} 问题 当前 ${paramName} 监测查询值为:${paramName}
     **/
    private String summaryModel;
    /**创建者*/
    private String creator;
    private Date createTime;
    /**修改者*/
    private String updater;
    private Date updateTime;
}
