package www.bwsensing.com.common.core.event;

import com.alibaba.cola.dto.Response;
import www.bwsensing.com.event.EventI;

import java.util.concurrent.ExecutorService;

/**
 * event handler
 *
 * @author shawnzhan.zxy
 * @date 2017/11/20
 */
public interface EventHandlerI<R extends Response, E extends EventI> {

    /**
     * 获取执行者
     * @return
     */
    default public ExecutorService getExecutor(){
        return null;
    }

    /**
     * 执行方法
     * @param e
     * @return
     */
    R execute(E e);
}
