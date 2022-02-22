package www.bwsensing.com.common.core.event;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import www.bwsensing.com.event.DomainEventI;
import javax.annotation.Resource;

/**
 * DomainEventPublisher, this is for demo purpose, the Event is sent to EventBus
 *
 * Normally DomainEvent should be sent to Messaging Middleware
 * @author Frank Zhang
 * @date 2019-01-04 11:05 AM
 */
@Profile("test")
@Component
public class TestDomainEventPublisherImpl implements DomainEventPublisher{

    @Resource
    private EventBusI eventBus;

    @Autowired
    private RocketMQTemplate rocketMqTemplate;

    @Override
    public void publish(DomainEventI domainEvent) {
        eventBus.fire(domainEvent);
        Message<DomainEventI> message = MessageBuilder.withPayload(domainEvent).build();
        rocketMqTemplate.send(domainEvent.getTopic(),message);
    }

    @Override
    public void asyncPublish(String destination,DomainEventI domainEvent, SendCallback sendCallback) {
        eventBus.asyncFire(domainEvent);
        Message<DomainEventI> message = MessageBuilder.withPayload(domainEvent).build();
        rocketMqTemplate.asyncSend(destination,message,sendCallback);
    }

    @Override
    public void transactionPublish(DomainEventI domainEvent) {
        eventBus.fire(domainEvent);
    }
}

