package www.bwsensing.com.event;


/**
 * @author Frank
 */
public interface MessageQueueEventI extends EventI {
    /**
     * 获取
     * @return
     */
    String getEventType();

    /**
     * 获取
     * @return
     */
    String getEventTopic();
}
