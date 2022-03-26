package www.bwsensing.com.event;

/**
 * @author macos-zyj
 */
public interface ClientStreamEventI extends DomainEventI {
    /**
     * 获取任务名称
     * @return
     */
    String getJobName();

    /**
     * 获取对应的Topic
     * @return
     */
    String getTopic();

    /**
     * 获取推送方式
     * @return
     */
    String getMessageKind();

    /**
     * 获取处理编码
     * @return
     */
    String getHandlerCode();

    /**
     * 获取对应的数据消息
     * @return
     */
    String getMessage();
}
