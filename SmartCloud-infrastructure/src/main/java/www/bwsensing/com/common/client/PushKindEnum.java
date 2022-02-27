package www.bwsensing.com.common.client;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */
public enum PushKindEnum {
    /**
     * Mqtt
     */
    MQTT("mqtt"),
    /**
     * RocketMq
     */
    ROCKETMQ("rocketMq");
    private String messageKind;

    PushKindEnum(String messageKind) {
        this.messageKind = messageKind;
    }

    public static PushKindEnum getPushKind(String messageKind){
        for (PushKindEnum kind : values()) {
            if (kind.getMessageKind().equals(messageKind)){
                return kind;
            }
        }
        throw new BizException("MESSAGE_KIND_NOT_FOUND");
    }

    public String getMessageKind() {
        return messageKind;
    }
}
