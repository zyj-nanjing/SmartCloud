package www.bwsensing.com.gatewayimpl;

import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.convertor.AlarmTemplateConvertor;
import www.bwsensing.com.convertor.AlertParamConvertor;
import www.bwsensing.com.convertor.ItemsConvertor;
import www.bwsensing.com.convertor.SensorModelConvertor;
import www.bwsensing.com.domain.device.alert.AlertParam;
import www.bwsensing.com.domain.device.alert.AlertTemplate;
import www.bwsensing.com.domain.gateway.AlertTemplateGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.gatewayimpl.database.AlertParamMapper;
import www.bwsensing.com.gatewayimpl.database.AlertTemplateMapper;
import www.bwsensing.com.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.gatewayimpl.database.SensorModelMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertParamDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertTemplateDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorModelDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class AlertTemplateGatewayImpl implements AlertTemplateGateway {
    @Resource
    private AlertTemplateMapper templateMapper;
    @Resource
    private AlertParamMapper alertParamMapper;
    @Resource
    private SensorModelMapper modelMapper;
    @Resource
    private MonitorItemsMapper itemMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void save(AlertTemplate template) {
        AlertTemplateDO dataObject = AlarmTemplateConvertor.toDateObject(template);
        validatePrefix(dataObject.getNamePrefix(),null);
        validateModel(dataObject.getModelNo());
        templateMapper.saveTemplate(dataObject);
        saveTemplateParam(dataObject.getId(),template.getAlertParams());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void update(AlertTemplate template) {
        AlertTemplateDO dataObject = AlarmTemplateConvertor.toDateObject(template);
        validatePrefix(dataObject.getNamePrefix(),template.getId());
        if (null != template.getModelNo()){
            validateModel(dataObject.getModelNo());
        }
        templateMapper.updateTemplate(dataObject);
        saveTemplateParam(dataObject.getId(),template.getAlertParams());
    }

    @Override
    public AlertTemplate getAlertTemplateById(Integer templateId) {
        AlertTemplateDO templateDo = templateMapper.selectTemplateById(templateId);
        if(null != templateDo){
            if (templateDo .getGroupId().equals(tokenGateway.getTokenInfo().getGroupId())){
                AlertTemplate templateDomain = AlarmTemplateConvertor.toDomainObject(templateDo);
                SensorModelDO modelDo = modelMapper.selectModelById(templateDo.getModelNo());
                templateDomain.setSensorModel(SensorModelConvertor.toDomainObject(modelDo));
                templateDomain.getAlertParams().forEach(alertParam -> {
                    MonitorItemsDO itemsDo = itemMapper.selectItemById(alertParam.getParamNo());
                    alertParam.setMonitorItem(ItemsConvertor.toDomain(itemsDo));
                });
                return templateDomain;
            } else {
                throw new BizException("THIS_TEMPLATE_NOT_PERMISSION","无权选择该预警模板");
            }
        }
        throw new BizException("TEMPLATE_NOT_FOUND","预警模板不存在");
    }


    private void saveTemplateParam(Integer templateId, List<AlertParam> paramCollection){
        alertParamMapper.deleteParamByTempId(templateId);
        paramCollection.forEach(alertParam -> {
            alertParam.validate();
            alertParam.create();
            validateItem(alertParam.getParamNo());
            AlertParamDO dataObject = AlertParamConvertor.toDateObject(alertParam);
            dataObject.setTemplateId(templateId);
            alertParamMapper.saveParam(dataObject);
        });
    }

    private  void validatePrefix(String namePrefix,Integer id){
        AlertTemplateDO queryTemplate = new AlertTemplateDO();
        queryTemplate.setNamePrefix(namePrefix);
        List<AlertTemplateDO> dataResult = templateMapper.selectTemplatesBySort(queryTemplate);
        queryTemplate.setNamePrefix(namePrefix);
        if(null != id){
            AlertTemplateDO templateResult = templateMapper.selectTemplateById(id);
            if (templateResult.getNamePrefix().equals(namePrefix)){
                return;
            }
        }
        if(dataResult.size()> 0){
            throw new BizException("NAME_PREFIX_EXIST","告警前缀已存在");
        }
    }

    private void validateModel(Integer modelId){
        if(null == modelMapper.selectModelById(modelId)){
            throw new BizException("SENSOR_MODEL_NOT_FOUND","产品型号不存在!");
        }
    }

    private void validateItem(Integer itemId){
        if(null == itemMapper.selectItemById(itemId)){
            throw new BizException("MONITOR_ITEM_NOT_FOUND","检测项不存在!");
        }
    }
}
