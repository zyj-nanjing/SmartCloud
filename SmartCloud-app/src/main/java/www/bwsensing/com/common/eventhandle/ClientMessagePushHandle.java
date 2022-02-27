package www.bwsensing.com.common.eventhandle;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.domainevent.ClientMessagePushEvent;

@CatchAndLog
@Slf4j
@EventHandler
public class ClientMessagePushHandle implements EventHandlerI<Response, ClientMessagePushEvent> {
    @Override
    public Response execute(ClientMessagePushEvent event) {
        log.info("Execute success message:{}",event.getMessage());
        return Response.buildSuccess();
    }
}
