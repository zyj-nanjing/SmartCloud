package www.bwsensing.com.common.core.event;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.event.EventI;


/**
 * EventBus interface
 * @author shawnzhan.zxy
 * @date 2017/11/20
 */
public interface EventBusI {

    /**
     * Send event to EventBus
     *
     * @param event
     * @return Response
     */
    Response fire(EventI event);

    /**
     * fire all handlers which registed the event
     *
     * @param event
     * @return Response
     */
    void fireAll(EventI event);

    /**
     * Async fire all handlers
     * @param event
     */
    void asyncFire(EventI event);
}
