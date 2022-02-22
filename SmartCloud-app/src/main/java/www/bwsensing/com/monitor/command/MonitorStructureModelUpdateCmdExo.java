package www.bwsensing.com.monitor.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.monitor.gateway.StructureModelGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.monitor.model.model.MonitorPositionModel;
import www.bwsensing.com.domain.monitor.model.model.MonitorStructureModel;
import www.bwsensing.com.monitor.dto.command.PositionModelUpdateCmd;
import www.bwsensing.com.monitor.dto.command.StructureModelUpdateCmd;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorStructureModelMapper;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorStructureModelDO;
import www.bwsensing.com.device.common.MonitorPositionValid;

import static java.util.stream.Collectors.toList;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class MonitorStructureModelUpdateCmdExo {
    @Resource
    private StructureModelGateway monitorStructureGateway;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private MonitorStructureModelMapper structureModelMapper;

    public Response execute(StructureModelUpdateCmd updateCmd){
      TokenData tokenData = tokenGateway.getTokenInfo();
      MonitorStructureModel domainObject = new MonitorStructureModel();
      BeanUtils.copyProperties(updateCmd,domainObject);
      updateModel(updateCmd.getId(),domainObject);
      domainObject.setPositionList(toDomainModelList(updateCmd.getPositionList()));
      domainObject.setGroupId(tokenData.getGroupId());
      domainObject.saveOrUpdate(tokenData.getAccountName());
      MonitorPositionValid.validatePosition(domainObject.getPositionList());
      monitorStructureGateway.updateModel(domainObject);
      domainObject.saveOrUpdate(tokenData.getAccountName());
      return  Response.buildSuccess();
    }

    private List<MonitorPositionModel> toDomainModelList(List<PositionModelUpdateCmd> positionModelList){
        return positionModelList.stream().map(MonitorStructureModelUpdateCmdExo::toDomain).collect(toList());
    }
    private static MonitorPositionModel toDomain(PositionModelUpdateCmd saveCmd){
        MonitorPositionModel positionModel = new MonitorPositionModel();
        BeanUtils.copyProperties(saveCmd,positionModel);
        return positionModel;
    }
    private void updateModel(int id,MonitorStructureModel updateModel){
        MonitorStructureModelDO lastModel = structureModelMapper.selectStructureModelById(id);
        if (null == lastModel){
            throw new BizException("MONITOR_STRUCTURE_NOT_FOUNT_ERROR","编号对应的结构物模板不存在");
        }
        updateModel.setStructureCode(lastModel.getStructureCode());
        updateModel.setVersion(lastModel.getVersion());
        if(null == updateModel.getIsPublic()){
            updateModel.setIsPublic(lastModel.getIsPublic());
        }
    }
}
