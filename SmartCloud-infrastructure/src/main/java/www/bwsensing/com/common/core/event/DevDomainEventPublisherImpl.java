package www.bwsensing.com.common.core.event;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import www.bwsensing.com.event.DomainEventI;
import javax.annotation.Resource;

/**
 * DomainEventPublisher, this is for demo purpose, the Event is sent to EventBus
 *
 * Normally DomainEvent should be sent to Messaging Middleware
 * TODO 整合中间件(RocketMq)
 * @author Frank Zhang
 * @date 2019-01-04 11:05 AM
 */
@Profile("dev")
@Component
public class DevDomainEventPublisherImpl implements DomainEventPublisher{

    @Resource
    private EventBusI eventBus;

    @Override
    public void publish(DomainEventI domainEvent) {
        eventBus.fire(domainEvent);
    }

    @Override
    public void transactionPublish(DomainEventI domainEvent) {
        eventBus.fire(domainEvent);
    }
}