package www.bwsensing.com.domainevent;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import www.bwsensing.com.event.DomainEventI;

/**
 * @author macos-zyj
 */
@Data
public class DeviceDataComputationEvent<T> implements DomainEventI {

    private Integer deviceId;

    private Integer deviceComputationId;

    /**
     * 触发时间
     */
    private LocalDateTime triggerTime;

    private List<T> dataCollections;

}
