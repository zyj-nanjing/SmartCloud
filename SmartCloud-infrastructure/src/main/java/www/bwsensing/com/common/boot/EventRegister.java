package www.bwsensing.com.common.boot;

import com.alibaba.cola.exception.SysException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.constant.ColaConstant;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.common.core.event.EventHub;
import www.bwsensing.com.event.EventI;
import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * EventRegister
 *
 * @author shawnzhan.zxy
 * @date 2017/11/20
 */
@Component
public class EventRegister{

    @Resource
    private EventHub eventHub;

    private Class<? extends EventI> getEventFromExecutor(Class<?> eventExecutorClz) {
        Method[] methods = eventExecutorClz.getDeclaredMethods();
        for (Method method : methods) {
            if (isExecuteMethod(method)){
                return checkAndGetEventParamType(method);
            }
        }
        throw new SysException("Event param in " + eventExecutorClz + " " + ColaConstant.EXE_METHOD
                + "() is not detected");
    }

    public void doRegistration(EventHandlerI eventHandler){
        Class<? extends EventI> eventClz = getEventFromExecutor(eventHandler.getClass());
        eventHub.register(eventClz, eventHandler);
    }

    private boolean isExecuteMethod(Method method){
        return ColaConstant.EXE_METHOD.equals(method.getName()) && !method.isBridge();
    }

    private Class checkAndGetEventParamType(Method method){
        Class<?>[] exeParams = method.getParameterTypes();
        if (exeParams.length == 0){
            throw new SysException("Execute method in "+method.getDeclaringClass()+" should at least have one parameter");
        }
        if(!EventI.class.isAssignableFrom(exeParams[0]) ){
            throw new SysException("Execute method in "+method.getDeclaringClass()+" should be the subClass of Event");
        }
        return exeParams[0];
    }
}

