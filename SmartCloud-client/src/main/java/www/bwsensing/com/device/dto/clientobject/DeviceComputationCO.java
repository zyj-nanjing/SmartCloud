package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class DeviceComputationCO extends DTO {
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

    /** 执行状态 */
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getExecuteId() {
        return executeId;
    }

    public void setExecuteId(String executeId) {
        this.executeId = executeId;
    }

    public Integer getDataComputationId() {
        return dataComputationId;
    }

    public void setDataComputationId(Integer dataComputationId) {
        this.dataComputationId = dataComputationId;
    }

    public Integer getComputationKind() {
        return computationKind;
    }

    public void setComputationKind(Integer computationKind) {
        this.computationKind = computationKind;
    }

    public Boolean getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(Boolean concurrent) {
        this.concurrent = concurrent;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getHandleKind() {
        return handleKind;
    }

    public void setHandleKind(Integer handleKind) {
        this.handleKind = handleKind;
    }

    public Integer getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(Integer functionCode) {
        this.functionCode = functionCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
