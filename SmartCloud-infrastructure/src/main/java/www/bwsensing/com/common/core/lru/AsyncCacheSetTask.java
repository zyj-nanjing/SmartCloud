package www.bwsensing.com.common.core.lru;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import www.bwsensing.com.domainevent.LruCachePushEvent;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Service
public class AsyncCacheSetTask {
    @Resource
    private DomainEventPublisher eventPublisher;


    @Async
    public<K,V> void pushCacheChange(String bizId,LruLinkedCacheMap<K,V> cache){
        log.debug("push to database:{}",bizId);
        LruCachePushEvent<K,V> pushEvent = new LruCachePushEvent<>();
        pushEvent.setBizId(bizId);
        pushEvent.setCache(cache);
        pushEvent.setTimestamp(System.currentTimeMillis());
        eventPublisher.publish(pushEvent);
    }
}
