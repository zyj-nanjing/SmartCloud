package www.bwsensing.com.common.eventhandle;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import lombok.extern.slf4j.Slf4j;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.domainevent.MqttStreamTestEvent;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Slf4j
@EventHandler
public class MqttStreamTestHandle implements EventHandlerI<Response, MqttStreamTestEvent> {
    @Override
    public Response execute(MqttStreamTestEvent mqttStreamTestEvent) {
        return Response.buildSuccess();
    }
}
