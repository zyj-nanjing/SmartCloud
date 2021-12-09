package www.bwsensing.com.common.core.event;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import com.alibaba.cola.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.thread.NamedThreadFactory;
import www.bwsensing.com.event.EventI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Event Bus
 *
 * @author shawnzhan.zxy
 * @date 2017/11/20
 */
@Slf4j
@Component
public class EventBus implements EventBusI {

    /**
     * 默认线程池
     *     如果处理器无定制线程池，则使用此默认
     */
    ExecutorService defaultExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1,
            0L,TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1000),
            new NamedThreadFactory("ASYNC-EVENT-POOL"));

    @Autowired
    private EventHub eventHub;

    @SuppressWarnings("unchecked")
    @Override
    public Response fire(EventI event) {
        Response response = null;
        EventHandlerI eventHandlerI = null;
        try {
            eventHandlerI = eventHub.getEventHandler(event.getClass()).get(0);
            response = eventHandlerI.execute(event);
        }catch (Exception exception) {
            response = handleException(eventHandlerI, exception);
        }
        return response;
    }

    @Override
    public void fireAll(EventI event){
        eventHub.getEventHandler(event.getClass()).stream().map(p->{
            Response response = null;
            try {
                response = p.execute(event);
            }catch (Exception exception) {
                response = handleException(p, exception);
            }
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public void asyncFire(EventI event){
        eventHub.getEventHandler(event.getClass()).parallelStream().map(p->{
            Response response = null;
            try {
                if(null != p.getExecutor()){
                    p.getExecutor().submit(()->p.execute(event));
                }else{
                    defaultExecutor.submit(()->p.execute(event));
                }
            }catch (Exception exception) {
                response = handleException(p, exception);
            }
            return response;
        }).collect(Collectors.toList());
    }

    private Response handleException(EventHandlerI handler, Exception exception) {
        log.error(exception.getMessage(), exception);
        Class responseClz = eventHub.getResponseRepository().get(handler.getClass());
        Response response;
        try {
            response = (Response) responseClz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SysException(e.getMessage());
        }
        if (exception instanceof BaseException) {
            response.setErrCode(((BaseException) exception).getErrCode());
        }
        else {
            response.setErrCode("SYS_ERROR");
        }
        response.setErrMessage(exception.getMessage());
        return response;
    }
}
