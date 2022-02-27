package www.bwsensing.com.domainevent;

import lombok.Data;
import java.util.LinkedHashMap;
import www.bwsensing.com.event.DomainEventI;

/**
 * @author macos-zyj
 */
@Data
public class LruCachePushEvent<K,V> implements DomainEventI {
    private String bizId;
    private long timestamp;
    private LinkedHashMap<K,V> cache;

}
