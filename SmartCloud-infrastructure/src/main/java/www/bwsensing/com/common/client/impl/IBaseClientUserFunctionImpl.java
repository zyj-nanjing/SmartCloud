package www.bwsensing.com.common.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.client.ClientUserFunction;
import www.bwsensing.com.common.client.ClientStreamContainer;
import www.bwsensing.com.domainevent.ClientMessagePushEvent;
import org.apache.rocketmq.streams.common.functions.MapFunction;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class IBaseClientUserFunctionImpl<O> implements ClientUserFunction<O>  {

    @Override
    public MapFunction<String,O> getUserFunction(String namespace, String jobName) {
        return message -> {
            ClientStreamContainer.offer(initMessage(namespace, jobName, message));
            return message.toString();
        };
    }


    private ClientMessagePushEvent<O> initMessage(String namespace, String jobName, O message) {
        ClientMessagePushEvent<O> publishEvent = new ClientMessagePushEvent<>();
        publishEvent.setNamespace(namespace);
        publishEvent.setJobName(jobName);
        publishEvent.setMessage(message);
        return publishEvent;
    }

}
