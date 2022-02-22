package www.bwsensing.com.device.gatewayimpl;

import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.device.convertor.SensorModelConvertor;
import www.bwsensing.com.domain.device.model.SensorModel;
import www.bwsensing.com.domain.device.gateway.SensorModelGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.device.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.device.gatewayimpl.database.SensorModelMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorModelDO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class SensorModelGatewayImpl implements SensorModelGateway {
    @Resource
    private SensorModelMapper sensorModelMapper;
    @Resource
    private SensorMapper sensorMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveModel(SensorModel saveModel) {
        saveModel.create();
        SensorModelDO modelDo = SensorModelConvertor.toDataObject(saveModel);
        if (sensorModelMapper.countModelByName(modelDo.getModelName())>0){
            throw new BizException("MODEL_HAVE_EXIST","模型名称已存在!");
        }
        sensorModelMapper.saveModel(modelDo);
        saveModel.getPrototypeList().forEach(type->{
            sensorModelMapper.saveModelLink(modelDo.getId(), type.getId());
        });
    }
    
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateModel(SensorModel updateModel) {
        SensorModelDO modelDo = SensorModelConvertor.toDataObject(updateModel);
        if (StringUtils.isNotEmpty(updateModel.getModelName()) ){
            SensorModelDO dataObject = sensorModelMapper.selectModelById(modelDo.getId());
            if (!dataObject.getModelName().equals(updateModel.getModelName())){
                if (sensorModelMapper.countModelByName(modelDo.getModelName())>0){
                    throw new BizException("MODEL_HAVE_EXIST","模型名称已存在!");
                }
            }
        }
        sensorModelMapper.updateModel(modelDo);
        sensorModelMapper.deleteLink(updateModel.getId());
        updateModel.getPrototypeList().forEach(type->{
            sensorModelMapper.saveModelLink(modelDo.getId(), type.getId());
        });
    }

    @Override
    public void deleteModel(Integer modelId) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        if(null == sensorModelMapper.selectModelById(modelId)){
            throw new BizException("NO_PRODUCT_MODEL_FOUND","该型号不存在!");
        }
        if (!RoleConstant.ROOT_ADMIN.equals(tokenData.getRole())){
            throw new BizException("NO_PERMISSION_DELETE","无权进行删除!");
        }
        if (null!= sensorMapper.selectSensorByModelId(modelId)&&sensorMapper.selectSensorByModelId(modelId).size()>0){
            throw new BizException("NO_ALLOW_DELETE","该型号下有实例化传感器无法进行删除!");
        }
        sensorModelMapper.deleteModel(modelId);
    }
}
