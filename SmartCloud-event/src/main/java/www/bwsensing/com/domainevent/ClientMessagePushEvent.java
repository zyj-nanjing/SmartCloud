package www.bwsensing.com.domainevent;

import lombok.Data;
import www.bwsensing.com.event.DomainEventI;

/**
 * @author macos-zyj
 */
@Data
public class ClientMessagePushEvent<M> implements DomainEventI {
    /**
     * 命名空间
     */
    private String namespace;

    /***
     * 任务名称
     */
    private String jobName;

    /**
     * 接收的消息
     */
    private M message;
}
