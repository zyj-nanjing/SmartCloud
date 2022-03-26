package www.bwsensing.com.stream.object;

import lombok.Data;
import www.bwsensing.com.event.ClientStreamEventI;

/**
 * @author macos-zyj
 */
@Data
public class ClientStream implements ClientStreamEventI {
    private String message;


    private String messageKind;

    private String handlerCode;

    private String topic;

    private String jobName;

    public ClientStream() {
    }

    public ClientStream(String messageKind) {
        this.messageKind = messageKind;
    }

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

    @Override
    public String getMessage() {
        return message;
    }
}
