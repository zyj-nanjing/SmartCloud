package www.bwsensing.com.common.core.event;

import www.bwsensing.com.event.DomainEventI;
/**
 * @author macos-zyj
 */
public interface DomainEventPublisher {
    /**
     * 提交
     * @param domainEvent
     */
    void publish(DomainEventI domainEvent);

    /**
     * 事务提交
     * @param domainEvent
     */
    void transactionPublish(DomainEventI domainEvent);
}
