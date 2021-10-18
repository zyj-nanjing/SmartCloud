package www.bwsensing.com.service;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import www.bwsensing.com.api.ISensorModelService;
import www.bwsensing.com.command.SensorModelCmdExo;
import www.bwsensing.com.convertor.SensorModelCoConvertor;
import www.bwsensing.com.domain.device.SensorModel;
import www.bwsensing.com.domain.gateway.PrototypeGateway;
import www.bwsensing.com.domain.gateway.SensorModelGateway;
import www.bwsensing.com.domain.monitor.MonitorPrototype;
import www.bwsensing.com.dto.SensorModelVO;
import www.bwsensing.com.dto.clientobject.ImportResultCO;
import www.bwsensing.com.dto.command.SensorModelSaveCmd;
import www.bwsensing.com.dto.command.SensorModelUpdateCmd;
import www.bwsensing.com.dto.clientobject.SensorModelCO;
import www.bwsensing.com.gatewayimpl.database.SensorModelMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorModelDO;

/**
 * @// TODO: 2021/8/22  添加传感器模板添加修改权限
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class SensorModelServiceImpl implements ISensorModelService {
    @Resource
    private SensorModelGateway sensorModelGateway;
    @Resource
    private PrototypeGateway prototypeGateway;
    @Resource
    private SensorModelMapper modelMapper;
    @Resource
    private SensorModelCmdExo modelCmdExo;

    @Override
    public Response saveModel(SensorModelSaveCmd saveCmd) {
        SensorModel sensorModel = new SensorModel();
        BeanUtils.copyProperties(saveCmd,sensorModel);
        setPrototypeList(saveCmd.getPrototypeList(),sensorModel);
        sensorModelGateway.saveModel(sensorModel);
        return Response.buildSuccess();
    }

    @Override
    public Response updateModel(SensorModelUpdateCmd updateCmd) {
        SensorModel sensorModel = new SensorModel();
        BeanUtils.copyProperties(updateCmd,sensorModel);
        setPrototypeList(updateCmd.getPrototypeList(),sensorModel);
        sensorModelGateway.updateModel(sensorModel);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteModel(Integer modelId) {
        sensorModelGateway.deleteModel(modelId);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<SensorModelCO> queryAllModels() {
        List<SensorModelDO> modelArrays = modelMapper.selectAllProductModels();
        if (null != modelArrays&&modelArrays.size()>0){
            return MultiResponse.of(SensorModelCoConvertor.toClientObjectArray(modelArrays));
        } else{
            return MultiResponse.of(new ArrayList<>());
        }
    }

    @Override
    public SingleResponse<SensorModelCO> selectModelById(Integer modelId) {
        Assert.notNull(modelId,"型号编号不能为空!");
        SensorModelDO result = modelMapper.selectModelById(modelId);
        if (null != result){
            return SingleResponse.of(SensorModelCoConvertor.toClientObject(result));
        } else{
            throw new BizException("SENSOR_MODEL_NOT_EXIST","该传感器型号不存在!");
        }
    }

    @Override
    public SingleResponse<ImportResultCO> importModels(List<SensorModelVO> importCollection) {
        return modelCmdExo.execute(importCollection);
    }

    private void setPrototypeList(List<Integer> ids,SensorModel sensorModel){
        List<MonitorPrototype> prototypeList = prototypeGateway.getMonitorPrototypesByIds(ids);
        if (prototypeList.size() > 0){
            sensorModel.setPrototypeList(prototypeList);
        } else{
            throw new BizException("PROTO_TYPE_LIST_ERROR","监测类型编号错误");
        }
    }

}
