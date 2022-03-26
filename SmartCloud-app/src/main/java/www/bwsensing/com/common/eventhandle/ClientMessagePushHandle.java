package www.bwsensing.com.common.eventhandle;


import com.alibaba.cola.extension.BizScenario;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.extension.ExtensionExecutor;
import www.bwsensing.com.stream.object.ClientStream;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.domainevent.ClientMessagePushEvent;
import www.bwsensing.com.device.dto.command.ClientMessageHandleCmd;
import www.bwsensing.com.device.extensionpoint.ClientMessageHandleExtPt;


/**
 * @author macos-zyj
 */
@CatchAndLog
@Slf4j
@EventHandler
public class ClientMessagePushHandle implements EventHandlerI<Response, ClientMessagePushEvent> {
    @Resource
    private ExtensionExecutor extensionExecutor;

    @Override
    public Response execute(ClientMessagePushEvent event) {
        ClientStream clientStream = JSONObject.parseObject(event.getMessage().toString(), ClientStream.class);
        ClientMessageHandleCmd handleCmd = initClientMessageHandleCmd(clientStream,event.getMessage().toString());
        extensionExecutor.executeVoid(ClientMessageHandleExtPt.class, handleCmd.getBizScenario(), extension -> extension.getAndHandler(handleCmd));
        return Response.buildSuccess();
    }

    private ClientMessageHandleCmd initClientMessageHandleCmd(ClientStream clientStream,String data){
        ClientMessageHandleCmd handleCmd = new ClientMessageHandleCmd();
        handleCmd.setJsonData(data);
        handleCmd.setScenario(clientStream.getHandlerCode());
        BizScenario scenario = BizScenario.valueOf(BizScenarioCode.BIZ_ID_CLOUD, BizScenarioCode.CLIENT_STREAM_MESSAGE,clientStream.getHandlerCode());
        handleCmd.setBizScenario(scenario);
        return handleCmd;
    }
}
