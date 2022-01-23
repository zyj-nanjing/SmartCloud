package www.bwsensing.com.common.core.event;

import com.alibaba.cola.exception.SysException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.event.EventI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件控制中枢
 * @author shawnzhan.zxy
 * @date 2017/11/20
 */
@SuppressWarnings("rawtypes")
@Component
public class EventHub {

    private HashMap<Class, List<EventHandlerI>> eventRepository = new HashMap<>();

    private Map<Class, Class> responseRepository = new HashMap<>();

    public HashMap<Class, List<EventHandlerI>> getEventRepository() {
        return eventRepository;
    }

    public void setEventRepository(HashMap<Class, List<EventHandlerI>> eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Map<Class, Class> getResponseRepository() {
        return responseRepository;
    }

    public List<EventHandlerI> getEventHandler(Class eventClass) {
        List<EventHandlerI> eventHandlerList = findHandler(eventClass);
        if (eventHandlerList == null || eventHandlerList.size() == 0) {
            throw new SysException(eventClass + " is not registered in eventHub, please register first");
        }
        return eventHandlerList;
    }

    /**
     * 注册事件
     * @param eventClz
     * @param executor
     */
    public void register(Class<? extends EventI> eventClz, EventHandlerI executor){
        List<EventHandlerI> eventHandlers = eventRepository.computeIfAbsent(eventClz, k -> new ArrayList<>());
        eventHandlers.add(executor);

    }

    private List<EventHandlerI> findHandler(Class<? extends EventI> eventClass){
        List<EventHandlerI> eventHandlerList = null;
        eventHandlerList = eventRepository.get(eventClass);
        return eventHandlerList;
    }

}