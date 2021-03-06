package www.bwsensing.com.device.command;

import com.alibaba.cola.dto.Response;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.convertor.AlertParamCoConvertor;
import www.bwsensing.com.domain.device.model.alert.AlertTemplate;
import www.bwsensing.com.domain.device.gateway.AlertTemplateGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.device.dto.command.AlarmTemplateSaveCmd;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class AlertTemplateSaveCmdExo {
    private static final BeanCopier DATA_COPY = BeanCopier.create(AlarmTemplateSaveCmd.class, AlertTemplate.class,false);
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private AlertTemplateGateway alarmTemplateGateway;

    public Response execute(AlarmTemplateSaveCmd saveCmd){
        AlertTemplate saveDomain = new AlertTemplate();
        TokenData tokenData = tokenGateway.getTokenInfo();
        DATA_COPY.copy(saveCmd,saveDomain,null);
        saveDomain.setGroupId(tokenData.getGroupId());
        saveDomain.addOrUpdate(tokenData.getAccountName());
        saveDomain.setAlertParams(AlertParamCoConvertor.toDomainCollection(saveCmd.getAlertParams()));
        alarmTemplateGateway.save(saveDomain);
        return  Response.buildSuccess();
    }


}
