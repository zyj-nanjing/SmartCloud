package www.bwsensing.com.event;


import java.io.Serializable;

/**
 * @author Frank
 */
public interface DomainEventI extends EventI, Serializable {
    /**
     * 获取对应的Topic
     * @return
     */
    String getTopic();
}
