package www.bwsensing.com.device.gatewayimpl;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.device.convertor.AlarmTemplateConvertor;
import www.bwsensing.com.device.convertor.AlertParamConvertor;
import www.bwsensing.com.device.convertor.ProductDataItemConvertor;
import www.bwsensing.com.device.convertor.ProductModelConvertor;
import www.bwsensing.com.domain.device.model.alert.AlertTemplate;
import www.bwsensing.com.domain.device.model.alert.AlertParam;
import www.bwsensing.com.domain.device.gateway.AlertTemplateGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.device.gatewayimpl.database.AlertTemplateMapper;
import www.bwsensing.com.device.gatewayimpl.database.AlertParamMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductModelMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertTemplateDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertParamDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductModelDO;

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
    private ProductModelMapper modelMapper;
    @Resource
    private ProductDataItemMapper itemMapper;
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
                ProductModelDO modelDo = modelMapper.getProductModelById(templateDo.getModelNo());
                templateDomain.setSensorModel(ProductModelConvertor.toDomainObject(modelDo));
                templateDomain.getAlertParams().forEach(alertParam -> {
                    ProductDataItemDO itemsDo = itemMapper.getProductDataItemById(alertParam.getParamNo());
                    alertParam.setMonitorItem(ProductDataItemConvertor.toDomain(itemsDo));
                });
                return templateDomain;
            } else {
                throw new BizException("THIS_TEMPLATE_NOT_PERMISSION","???????????????????????????");
            }
        }
        throw new BizException("TEMPLATE_NOT_FOUND","?????????????????????");
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
            throw new BizException("NAME_PREFIX_EXIST","?????????????????????");
        }
    }

    private void validateModel(Integer modelId){
        if(null == modelMapper.getProductModelById(modelId)){
            throw new BizException("SENSOR_MODEL_NOT_FOUND","?????????????????????!");
        }
    }

    private void validateItem(Integer itemId){
        if(null == itemMapper.getProductDataItemById(itemId)){
            throw new BizException("MONITOR_ITEM_NOT_FOUND","??????????????????!");
        }
    }
}
