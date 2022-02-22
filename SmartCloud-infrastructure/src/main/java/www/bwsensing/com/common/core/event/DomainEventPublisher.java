package www.bwsensing.com.common.core.event;

import org.apache.rocketmq.client.producer.SendCallback;
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
     * 异步发送
     * @param destination
     * @param domainEvent
     * @param sendCallback
     */
    void asyncPublish(String destination,DomainEventI domainEvent, SendCallback sendCallback);

    /**
     * 事务提交
     * @param domainEvent
     */
    void transactionPublish(DomainEventI domainEvent);
}
