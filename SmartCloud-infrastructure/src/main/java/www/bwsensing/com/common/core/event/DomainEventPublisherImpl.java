package www.bwsensing.com.common.core.event;

import org.springframework.stereotype.Component;
import org.apache.rocketmq.client.producer.SendCallback;
import www.bwsensing.com.common.annotation.ClientStreamRegister;
import www.bwsensing.com.event.ClientStreamEventI;
import www.bwsensing.com.event.DomainEventI;
import javax.annotation.Resource;

/**
 * DomainEventPublisher, this is for demo purpose, the Event is sent to EventBus
 *
 * Normally DomainEvent should be sent to Messaging Middleware
 * @author Frank Zhang
 * @date 2019-01-04 11:05 AM
 */
@Component
public class DomainEventPublisherImpl implements DomainEventPublisher{

    @Resource
    private EventBusI eventBus;


    @Override
    public void publish(DomainEventI domainEvent) {
        eventBus.fire(domainEvent);
    }

    @ClientStreamRegister
    @Override
    public void publishStream(ClientStreamEventI streamEvent) {
        eventBus.fire(streamEvent);
    }

    @Override
    public void asyncPublish(String destination,DomainEventI domainEvent, SendCallback sendCallback) {
        eventBus.asyncFire(domainEvent);
    }

    @Override
    public void transactionPublish(DomainEventI domainEvent) {
        eventBus.fire(domainEvent);
    }
}

