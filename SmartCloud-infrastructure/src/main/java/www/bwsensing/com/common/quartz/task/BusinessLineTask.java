package www.bwsensing.com.common.quartz.task;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import www.bwsensing.com.domainevent.DeviceDataComputationEvent;

/**
 * 业务定时任务调度
 * @author macos-zyj
 */
@Component("businessTask")
public class BusinessLineTask {
    @Autowired
    private DomainEventPublisher eventPublisher;

    public void taskDeviceDataComputation(Integer deviceId,Integer deviceComputationId) {
        DeviceDataComputationEvent dataComputationEvent = new DeviceDataComputationEvent();
        dataComputationEvent.setDeviceId(deviceId);
        dataComputationEvent.setDeviceComputationId(deviceComputationId);
        eventPublisher.publish(dataComputationEvent);
    }

}
