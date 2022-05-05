package www.bwsensing.com.domain.device.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import www.bwsensing.com.domain.common.UuidUtils;
import www.bwsensing.com.domain.device.model.data.model.ComputationKind;

/**
 * @author macos-zyj
 */
@Data
public class DeviceComputation {
    private static final String INVOKE_TARGET ="businessTask.taskDeviceDataComputation(#{deviceId},#{deviceComputationId})";
    private static final String DEFAULT_NAME = "计算模型(#{executeId})";
    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备Id
     */
    private Integer deviceId;

    /**
     * 执行编码
     */
    private String executeId = UuidUtils.generateShortUuid();

    /**
     * 计算模型编号
     */
    private Integer dataComputationId;

    /**
     * 计算触发类型
     */
    private ComputationKind computationKind;

    /**
     * 是否并发执行
     */
    private Boolean concurrent;

    /**
     * cron执行表达式
     */
    private String cronExpression;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 任务编号
     */
    private Integer jobId;

    public  void saveCheck(){
        if (StringUtils.isEmpty(executeId)){
            this.executeId = UuidUtils.generateShortUuid();
        }
        if(null == status){
            this.status = true;
        }
        if(null == concurrent){
            this.concurrent = true;
        }
    }


    public String initInvokeTarget() {
        return INVOKE_TARGET.replace("#{deviceId}",
                deviceId+"").replace("#{deviceComputationId}",dataComputationId+"");
    }

    public String initScheduledName() {
        return DEFAULT_NAME.replace("#{executeId}",executeId);
    }
}
