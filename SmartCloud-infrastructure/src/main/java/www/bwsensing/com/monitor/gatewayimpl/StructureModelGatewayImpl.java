package www.bwsensing.com.monitor.gatewayimpl;
import com.alibaba.cola.exception.BizException;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.common.utills.DateUtils;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.monitor.convertor.PositionModelConvertor;
import www.bwsensing.com.monitor.convertor.StructureModelConvertor;
import www.bwsensing.com.domain.monitor.gateway.StructureModelGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.monitor.model.MonitorStructure;
import www.bwsensing.com.domain.monitor.model.model.MonitorPositionModel;
import www.bwsensing.com.domain.monitor.model.model.MonitorStructureModel;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorPositionModelMapper;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorStructureModelMapper;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorPositionModelDO;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorStructureModelDO;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class StructureModelGatewayImpl implements StructureModelGateway {
    @Resource
    private MonitorStructureModelMapper structureModelMapper;
    @Resource
    private MonitorPositionModelMapper positionModelMapper;
    @Resource
    private TokenGateway tokenGateway;
    @Override
    public List<MonitorStructure> selectMonitorStructureBySort() {
        return null;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveModel(MonitorStructureModel monitorStructure) {
        monitorStructure.setGroupId(tokenGateway.getTokenInfo().getGroupId());
        MonitorStructureModelDO saveDataObject = StructureModelConvertor.toDataObject(monitorStructure);
        structureModelMapper.saveModel(saveDataObject);
        monitorStructure.getPositionList().forEach(position -> {
            position.setStructureCode(monitorStructure.getStructureCode());
            position.setModelId(saveDataObject.getId());
            position.setStructureVersion(monitorStructure.getVersion());
            position.create(saveDataObject.getCreator());
            savePositionModel(position);
        });
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateModel(MonitorStructureModel monitorStructure) {
        MonitorStructureModelDO updateModel = StructureModelConvertor.toDataObject(monitorStructure);
        if(StringUtils.isEmpty(monitorStructure.getPicture())){
            updateModel.setPicture(structureModelMapper.selectStructureModelByCode(monitorStructure.getStructureCode()).getPicture());
        }
        structureModelMapper.saveModel(updateModel);
        monitorStructure.getPositionList().forEach(position -> {
            position.setStructureCode(monitorStructure.getStructureCode());
            position.setModelId(updateModel.getId());
            if (null != position.getId()){
                MonitorPositionModelDO positionModelDo = positionModelMapper.selectPositionModelById(position.getId());
                position.setVersion(positionModelDo.getVersion());
                position.setCreator(positionModelDo.getCreator());
                position.setCreateTime(DateUtils.getNowDate());
                position.update();
            } else{
                position.create(monitorStructure.getCreator());
            }
            position.setStructureVersion(monitorStructure.getVersion());
            savePositionModel(position);
        });
    }

    @Override
    public void deleteByCode(String code) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        MonitorStructureModelDO structureModel = structureModelMapper.selectStructureModelByCode(code);
        if(null == structureModel){
            throw new BizException("NO_STRUCTURE_FOUND","?????????????????????!");
        }
        if (structureModel.getIsPublic()){
            if (!RoleConstant.ROOT_ADMIN.equals(tokenData.getRole())){
                throw new BizException("NO_PERMISSION_DELETE","??????????????????!");
            }
        } else {
            if (!(RoleConstant.GROUP_ADMIN.equals(tokenData.getRole())
                    ||RoleConstant.ROOT_ADMIN.equals(tokenData.getRole()))){
                throw new BizException("NO_PERMISSION_DELETE","??????????????????!");
            }
        }
        if (structureModelMapper.countStructureModelUseByCode(code) >0 ){
            throw new BizException("STRUCTURE_MODEL_IN_USE","?????????????????????????????????????????????!");
        }
        structureModelMapper.deleteModelByCode(code);
        positionModelMapper.deleteByStructureCode(code);
    }

    @Override
    public MonitorStructureModel getMonitorStructureModelByCode(String structureCode) {
        MonitorStructureModelDO dataObject = structureModelMapper.selectStructureModelByCode(structureCode);
        return StructureModelConvertor.toDomain(dataObject);
    }

    private void savePositionModel(MonitorPositionModel positionModel){
        MonitorPositionModelDO dataObject = PositionModelConvertor.toDataObject(positionModel);
        positionModelMapper.save(dataObject);
    }
}
