package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class DeviceComputationDO {
    /** 主键 */
    private Integer id;

    /** 设备编号 */
    private Integer deviceId;

    /** 执行编码 */
    private String executeId;

    /** 计算模型编号 */
    private Integer dataComputationId;

    /** 触发形式 */
    private Integer computationKind;

    /** 是否并发执行 */
    private Boolean concurrent;

    /** cron执行表达式 */
    private String cronExpression;

    /**
     * 处理类型
     */
    private Integer handleKind;

    /**
     * 时间范围处理函数
     */
    private Integer functionCode;

    /** 状态 */
    private Boolean status;

    /**
     * 任务编号
     */
    private Integer jobId;

    public DeviceComputationDO() {
    }

    public DeviceComputationDO(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
