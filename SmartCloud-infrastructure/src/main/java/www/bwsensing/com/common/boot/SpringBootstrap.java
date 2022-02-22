package www.bwsensing.com.common.boot;

import org.springframework.context.ApplicationContext;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author macos-zyj
 */
@Component
public class SpringBootstrap {
    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private EventRegister eventRegister;


    public void init() {
        Map<String, Object> eventHandlerBeans = applicationContext.getBeansWithAnnotation(EventHandler.class);
        eventHandlerBeans.values().forEach(
                eventHandler -> eventRegister.doRegistration((EventHandlerI) eventHandler)
        );
    }
}
