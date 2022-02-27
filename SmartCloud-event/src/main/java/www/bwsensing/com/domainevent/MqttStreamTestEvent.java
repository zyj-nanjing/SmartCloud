package www.bwsensing.com.domainevent;

import lombok.Data;
import www.bwsensing.com.event.ClientStreamEventI;

/**
 * @author macos-zyj
 */
@Data
public class MqttStreamTestEvent implements ClientStreamEventI {

    private String jobName;

    private String topic;

    private String messageKind = "mqtt";

    private String handlerCode;

    private String message;

    @Override
    public String getJobName() {
        return jobName;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public String getMessageKind() {
        return messageKind;
    }

    @Override
    public String getHandlerCode() {
        return handlerCode;
    }
}
