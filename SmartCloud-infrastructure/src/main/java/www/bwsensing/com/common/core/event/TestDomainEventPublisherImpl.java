package www.bwsensing.com.common.core.event;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.SendCallback;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.annotation.ClientStreamRegister;
import www.bwsensing.com.common.client.ClientScheduler;
import www.bwsensing.com.common.mqtt.MqttPushClient;
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
@Profile("test")
@Component
public class TestDomainEventPublisherImpl implements DomainEventPublisher{

    @Resource
    private EventBusI eventBus;

    @Resource
    private MqttPushClient mqttPushClient;

    @Resource
    private ClientScheduler clientScheduler;


    @Override
    public void publish(DomainEventI domainEvent) {
        eventBus.fire(domainEvent);
    }

    @ClientStreamRegister
    @Override
    public void publishStream(ClientStreamEventI streamEvent) {
        String pushMessage = JSONObject.toJSONString(streamEvent);
        mqttPushClient.publish(1,true,streamEvent.getTopic(),pushMessage);
        clientScheduler.mqttClientScheduler();
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

