package www.bwsensing.com.device.command;

import com.alibaba.cola.dto.Response;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.convertor.AlertParamCoConvertor;
import www.bwsensing.com.domain.device.model.alert.AlertTemplate;
import www.bwsensing.com.domain.device.gateway.AlertTemplateGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.device.dto.command.AlarmTemplateUpdateCmd;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class AlertTemplateUpdateCmdExo {
    private static final BeanCopier DATA_COPY = BeanCopier.create(AlarmTemplateUpdateCmd.class, AlertTemplate.class,false);
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private AlertTemplateGateway alarmTemplateGateway;

    public Response execute(AlarmTemplateUpdateCmd updateCmd){
        AlertTemplate updateDomain = new AlertTemplate();
        DATA_COPY.copy(updateCmd,updateDomain,null);
        TokenData tokenData = tokenGateway.getTokenInfo();
        updateDomain.setGroupId(tokenData.getGroupId());
        updateDomain.addOrUpdate(tokenData.getAccountName());
        updateDomain.setAlertParams(AlertParamCoConvertor.toDomainCollection(updateCmd.getAlertParams()));
        alarmTemplateGateway.update(updateDomain);
        return  Response.buildSuccess();
    }
}
