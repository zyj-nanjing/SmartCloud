package www.bwsensing.com.common.quartz.task;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import www.bwsensing.com.domainevent.DeviceDataComputationEvent;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 业务定时任务调度
 * @author macos-zyj
 */
@Component("businessTask")
public class BusinessLineTask {
    @Autowired
    private DomainEventPublisher eventPublisher;

    public void taskDeviceDataComputation(Integer deviceId,Integer deviceComputationId) {
        Date currentTime = new Date();
        DeviceDataComputationEvent dataComputationEvent = new DeviceDataComputationEvent();
        dataComputationEvent.setDeviceId(deviceId);
        dataComputationEvent.setDeviceComputationId(deviceComputationId);
        dataComputationEvent.setTriggerTime(LocalDateTime.now());
        eventPublisher.publish(dataComputationEvent);
    }

}
