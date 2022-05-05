package www.bwsensing.com.domainevent;

import lombok.Data;
import java.util.List;
import www.bwsensing.com.event.DomainEventI;

/**
 * @author macos-zyj
 */
@Data
public class DeviceDataComputationEvent<T> implements DomainEventI {

    private Integer deviceId;

    private Integer deviceComputationId;

    private List<T> dataCollections;

}
