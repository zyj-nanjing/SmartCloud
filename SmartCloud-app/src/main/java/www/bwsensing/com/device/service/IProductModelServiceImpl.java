package www.bwsensing.com.device.service;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import org.springframework.util.Assert;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.export.ProductModelVO;
import www.bwsensing.com.device.api.ProductModelService;
import www.bwsensing.com.domain.device.model.ProductModel;
import www.bwsensing.com.device.command.ProductModelCmdExo;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import www.bwsensing.com.device.dto.clientobject.ProductKindCO;
import www.bwsensing.com.device.dto.clientobject.ProductModelCO;
import www.bwsensing.com.device.dto.command.ProductModelSaveCmd;
import www.bwsensing.com.device.convertor.IndustryFieldConvertor;
import www.bwsensing.com.device.convertor.ProductKindCoConvertor;
import www.bwsensing.com.device.convertor.ProductModelConvertor;
import www.bwsensing.com.device.dto.command.ProductModelUpdateCmd;
import www.bwsensing.com.device.convertor.ProductModelCoConvertor;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import www.bwsensing.com.device.gatewayimpl.database.ProductKindMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductModelMapper;
import www.bwsensing.com.monitor.gatewayimpl.database.IndustryFieldMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductKindDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductModelDO;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.IndustryFieldDO;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class IProductModelServiceImpl implements ProductModelService {
    @Resource
    private ProductModelGateway productModelGateway;
    @Resource
    private ProductKindMapper productKindMapper;
    @Resource
    private ProductModelMapper modelMapper;
    @Resource
    private ProductModelCmdExo modelCmdExo;
    @Resource
    private IndustryFieldMapper industryFieldMapper;

    @Override
    public Response saveModel(ProductModelSaveCmd saveCmd) {
        ProductModelDO dataObject = new ProductModelDO();
        BeanUtils.copyProperties(saveCmd,dataObject);
        ProductModel domainModel = ProductModelConvertor.toDomainObject(dataObject);
        if (null != saveCmd.getIndustryFields()){
            List<IndustryFieldDO> industryFields = industryFieldMapper.getIndustryFieldsByIds(saveCmd.getIndustryFields());
            domainModel.setIndustryFields(IndustryFieldConvertor.toDomainArray(industryFields));
        }
        productModelGateway.saveModel(domainModel);
        return Response.buildSuccess();
    }

    @Override
    public Response updateModel(ProductModelUpdateCmd updateCmd) {
        ProductModelDO dataObject = new ProductModelDO();
        BeanUtils.copyProperties(updateCmd,dataObject);
        ProductModel domainModel = ProductModelConvertor.toDomainObject(dataObject);
        if (null != updateCmd.getIndustryFields()){
            List<IndustryFieldDO> industryFields = industryFieldMapper.getIndustryFieldsByIds(updateCmd.getIndustryFields());
            domainModel.setIndustryFields(IndustryFieldConvertor.toDomainArray(industryFields));
        }
        productModelGateway.updateModel(domainModel);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteModel(Integer modelId) {
        productModelGateway.deleteModel(modelId);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<ProductModelCO> queryProductModels() {
        List<ProductModelDO> modelArrays = modelMapper.queryProductModelBySort(new ProductModelDO());
        if (null != modelArrays&&modelArrays.size()>0){
            return MultiResponse.of(ProductModelCoConvertor.toClientObjectArray(modelArrays));
        } else{
            return MultiResponse.of(new ArrayList<>());
        }
    }

    @Override
    public MultiResponse<ProductKindCO> getProductModelKinds() {
        List<ProductKindDO> productKinds = productKindMapper.queryProductKindBySort(new ProductKindDO());
        return MultiResponse.of(ProductKindCoConvertor.toClientCollection(productKinds));
    }

    @Override
    public SingleResponse<ProductModelCO> selectModelById(Integer modelId) {
        Assert.notNull(modelId,"型号编号不能为空!");
        ProductModelDO result = modelMapper.getProductModelById(modelId);
        if (null != result){
            return SingleResponse.of(ProductModelCoConvertor.toClientObject(result));
        } else{
            throw new BizException("SENSOR_MODEL_NOT_EXIST","该传感器型号不存在!");
        }
    }

    @Override
    public SingleResponse<ImportResultCO> importModels(List<ProductModelVO> importCollection) {
        return modelCmdExo.execute(importCollection);
    }

}
