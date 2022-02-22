package www.bwsensing.com.monitor.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.domain.monitor.gateway.StructureModelGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.monitor.model.model.MonitorPositionModel;
import www.bwsensing.com.domain.monitor.model.model.MonitorStructureModel;
import www.bwsensing.com.monitor.dto.command.PositionModelSaveCmd;
import www.bwsensing.com.monitor.dto.command.StructureModelSaveCmd;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.device.common.MonitorPositionValid;
import static java.util.stream.Collectors.toList;
import javax.annotation.Resource;
import java.util.List;


/**
 * @author macos-zyj
 */
@Component
public class MonitorStructureModelSaveCmdExo {
    @Resource
    private StructureModelGateway monitorStructureGateway;
    @Resource
    private TokenGateway tokenGateway;

    public Response execute(StructureModelSaveCmd structureSaveCmd){
        TokenData tokenData = tokenGateway.getTokenInfo();
        if (null == structureSaveCmd.getIsPublic()){
            structureSaveCmd.setIsPublic(false);
        }
        validateAuth(tokenData, structureSaveCmd.getIsPublic());
        MonitorStructureModel domainObject = new MonitorStructureModel();
        BeanUtils.copyProperties(structureSaveCmd,domainObject);
        domainObject.setPositionList(toDomainModelList(structureSaveCmd.getPositionModels()));
        MonitorPositionValid.validatePosition(domainObject.getPositionList());
        domainObject.setGroupId(tokenData.getGroupId());
        domainObject.saveOrUpdate(tokenData.getAccountName());
        monitorStructureGateway.saveModel(domainObject);
        return Response.buildSuccess();
    }

    private void validateAuth(TokenData tokenData, boolean isPublic){
        if(isPublic){
            if(!tokenData.getRole().equals(RoleConstant.ROOT_ADMIN)){
                throw new BizException("ROLE_HAVE_NOT_PERMISSION","非超管角色无法创建公共结构物!");
            }
        }
    }
    private List<MonitorPositionModel> toDomainModelList(List<PositionModelSaveCmd> positionModelList){
        return positionModelList.stream().map(MonitorStructureModelSaveCmdExo::toDomain).collect(toList());
    }


    private static MonitorPositionModel toDomain(PositionModelSaveCmd saveCmd){
        MonitorPositionModel positionModel = new MonitorPositionModel();
        BeanUtils.copyProperties(saveCmd,positionModel);
        return positionModel;
    }

}
