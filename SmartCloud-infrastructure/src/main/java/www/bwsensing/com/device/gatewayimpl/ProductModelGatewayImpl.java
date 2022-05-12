package www.bwsensing.com.device.gatewayimpl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.exception.Assert;
import www.bwsensing.com.device.convertor.*;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.device.gatewayimpl.database.*;
import www.bwsensing.com.domain.device.model.ProductModel;
import www.bwsensing.com.common.utills.ObjectConvertUtils;
import www.bwsensing.com.domain.device.model.data.model.*;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.domain.device.model.DataItemSourceKind;
import www.bwsensing.com.domain.device.model.ExtraProductDataItem;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.*;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;

/**
 * @author macos-zyj
 */
@Component
public class ProductModelGatewayImpl implements ProductModelGateway {
    @Resource
    private ProductModelMapper productModelMapper;

    @Resource
    private DataModelMapper dataModelMapper;

    @Resource
    private DataModelItemMapper dataModelItemMapper;

    @Resource
    private ProductDeviceMapper productDeviceMapper;

    @Resource
    private ProductDataItemMapper productDataItemMapper;

    @Resource
    private ExtraProductDataItemMapper extraProductDataItemMapper;

    @Resource
    private ProductDataComputationModelMapper dataComputationModelMapper;

    @Resource
    private TokenGateway tokenGateway;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveModel(ProductModel saveModel) {
        saveModel.saveOrUpdate(tokenGateway.getTokenInfo().getAccountName());
        ProductModelDO modelDo = ProductModelConvertor.toDataObject(saveModel);
        if (productModelMapper.countModelByName(modelDo.getProductName())>0){
            throw new BizException("MODEL_HAVE_EXIST","产品已存在!");
        }
        productModelMapper.saveProductModel(modelDo);
        if (null != saveModel.getIndustryFields()){
            saveModel.getIndustryFields().forEach(value-> productModelMapper.saveProductModelWithIndustry(modelDo.getId(), value.getId()));
        }
    }
    
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateModel(ProductModel updateModel) {
        updateModel.saveOrUpdate(tokenGateway.getTokenInfo().getAccountName());
        ProductModelDO dataObject = productModelMapper.getProductModelById(updateModel.getId());
        if(null != dataObject){
            ProductModel dataBaseObject = ProductModelConvertor.toDomainObject(dataObject);
            ObjectConvertUtils.copyProperties(updateModel,dataBaseObject);
            if (!dataObject.getProductName().equals(updateModel.getProductName())){
                if (productModelMapper.countModelByName(updateModel.getProductName())>0){
                    throw new BizException("MODEL_HAVE_EXIST","产品已存在!");
                }
            }
            productModelMapper.updateProductModel(ProductModelConvertor.toDataObject(dataBaseObject));
            productModelMapper.deleteProductModelWithIndustryByModelId(updateModel.getId());
            if (null != updateModel.getIndustryFields()){
                updateModel.getIndustryFields().forEach(value-> productModelMapper.saveProductModelWithIndustry(updateModel.getId(), value.getId()));
            }
        } else {
            throw new BizException("NO_DATA_MODEL_FOUND","该数据模型不存在!");
        }
    }

    @Override
    public void deleteModel(Integer modelId) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        if(null == productModelMapper.getProductModelById(modelId)){
            throw new BizException("NO_PRODUCT_MODEL_FOUND","该型号不存在!");
        }
        if (!RoleConstant.ROOT_ADMIN.equals(tokenData.getRole())){
            throw new BizException("NO_PERMISSION_DELETE","无权进行删除!");
        }
        if (null!= productDeviceMapper.selectSensorByModelId(modelId)&& productDeviceMapper.selectSensorByModelId(modelId).size()>0){
            throw new BizException("NO_ALLOW_DELETE","该型号下有实例化传感器无法进行删除!");
        }
        productModelMapper.deleteProductModelById(modelId);
    }

    @Override
    public void addProductDataModel(ProductDataModel dataModel) {
        if(null == productModelMapper.getProductModelById(dataModel.getModelId())){
            throw new BizException("NO_PRODUCT_MODEL_FOUND","该产品型号不存在!");
        }
        if (SplitMethod.BY_SEPARATOR.equals(dataModel.getSplitMethod())&&null == dataModel.getSeparator()){
            throw new BizException("NO_FOUND_SEPARATOR","采用分隔符解析未添加分隔符");
        }
        if (SplitMethod.BY_DATA_LENGTH.equals(dataModel.getSplitMethod())&&null == dataModel.getBaseDataSize()){
            throw new BizException("NO_FOUND_DATA_LENGTH","采用数据长度解析未添加数据长度!");
        }
        DataModelDO dataModelDo = DataModelConvertor.toDataObject(dataModel);
        dataModelMapper.saveDataModel(dataModelDo);
    }

    @Override
    public void updateProductDataModel(ProductDataModel dataModel) {
        DataModelDO dataObject = dataModelMapper.queryDataModelById(dataModel.getId());
        if(null != dataObject){
            ProductDataModel dataBaseObject = DataModelConvertor.toDomain(dataObject);
            ObjectConvertUtils.copyProperties(dataModel,dataBaseObject);
            dataModelMapper.updateDataModel(DataModelConvertor.toDataObject(dataBaseObject));
        } else {
            throw new BizException("NO_DATA_MODEL_FOUND","该数据模型不存在!");
        }
    }

    @Transactional( rollbackFor = RuntimeException.class)
    @Override
    public void deleteProductDataModel(Integer id) {
        if(null == dataModelMapper.queryDataModelById(id)){
            throw new BizException("NO_DATA_MODEL_FOUND","该数据模型不存在!");
        }
        List<DataModelItemDO> itemCollection = dataModelItemMapper.queryDataModelItemByModelId(id);
        if(null != itemCollection && itemCollection.size() >0){
            throw new BizException("DATA_MODEL_HAS_ITEM","该数据模型存在对应数据解析项无法删除!");
        }
        dataModelMapper.deleteDataModelById(id);
    }

    @Override
    public void addProductDataModelItem(DataModelItem dataModelItem) {
        productDataItemFilter(dataModelItem);
        dataModelItemMapper.saveDataModelItem(DataModelItemConvertor.toDataObject(dataModelItem));
    }



    @Override
    public void updateProductDataModelItem(DataModelItem dataModelItem) {
        DataModelItemDO dataObject = dataModelItemMapper.queryDataModelItemById(dataModelItem.getId());
        if(null != dataObject){
            DataModelItem dataBaseObject = DataModelItemConvertor.toDomain(dataObject);
            ObjectConvertUtils.copyProperties(dataModelItem,dataBaseObject);
            productDataItemFilter(dataBaseObject);
            dataModelItemMapper.updateDataModelItem(DataModelItemConvertor.toDataObject(dataBaseObject));
        } else {
            throw new BizException("NO_DATA_ITEM_FOUND","该产品数据项不存在!");
        }
    }

    private void productDataItemFilter(DataModelItem dataModelItem){
        DataModelDO dataModelDo = dataModelMapper.queryDataModelById(dataModelItem.getModelId());
        if(null == dataModelDo){
            throw new BizException("NO_DATA_MODEL_FOUND","该产品数据模型不存在!");
        }
        if(SplitMethod.BY_DATA_LENGTH.equals(SplitMethod.getSplitMethod(dataModelDo.getSplitMethod()))){
            if(null == dataModelItem.getDataLength()||dataModelItem.getDataLength()==0){
                throw new BizException("DATA_LENGTH_NOT_FOUND","数据长度不存在!");
            }
        }
        if(SplitMethod.JSON.equals(SplitMethod.getSplitMethod(dataModelDo.getSplitMethod()))){
            if(StringUtils.isEmpty(dataModelItem.getKeyName())){
                throw new BizException("KEY_NAME_NOT_FOUND","JSON数据Key不存在!");
            }
        }
        dataItemKindFilter(dataModelItem);
    }

    private void dataItemKindFilter(DataModelItem dataModelItem) {
        switch (dataModelItem.getItemKind()){
            case UNIQUE_SN:
                if (null == dataModelItem.getUniqueCodeSize()|| dataModelItem.getUniqueCodeSize() == 0){
                    throw new BizException("UNIQUE_CODE_SIZE_NOT_FOUND","唯一编码长度不存在!");
                }
                dataModelItem.setDataFormat("^\\d{{size}}$");
                break;
            case FUNCTION_CODE:
            case DATA_SIZE:
                break;
            case DATA_INDEX:
                if (dataModelItem.getNeedTransform()){
                    if (StringUtils.isEmpty(dataModelItem.getCalculationFormula())){
                        throw new BizException("CALCULATION_NOT_FOUND","计算公式不存在!");
                    }
                    if (StringUtils.isEmpty(dataModelItem.getPlaceholder())){
                        throw new BizException("PLACEHOLDER_NOT_FOUND","占位符不存在!");
                    }
                }
                break;
            default:
        }
    }

    @Override
    public void deleteProductDataModelItem(Integer id) {
        DataModelItemDO dataModelItem = dataModelItemMapper.queryDataModelItemById(id);
        if(null == dataModelItem){
            throw new BizException("NO_DATA_MODEL_FOUND","该产品数据模型数据项不存在!");
        }
        dataModelItemMapper.deleteDataModelItemById(id);
    }

    @Override
    public void addProductDataItem(ProductDataItem productDataItem) {
        calculationFormulaFilter(productDataItem.getModelId(), productDataItem.getNeedTransform(), productDataItem.getCalculationFormula(), productDataItem.getPlaceholder());
        ProductDataItemDO dataObject = ProductDataItemConvertor.toDataObject(productDataItem);
        productDataItemMapper.saveDataItem(dataObject);
    }

    @Override
    public void updateProductDataItem(ProductDataItem productDataItem) {
        ProductDataItemDO dataObject = productDataItemMapper.getProductDataItemById(productDataItem.getId());
        if(null != dataObject){
            ProductDataItem dataBaseObject = ProductDataItemConvertor.toDomain(dataObject);
            ObjectConvertUtils.copyProperties(productDataItem,dataBaseObject);
            productDataItemMapper.updateDataItem(ProductDataItemConvertor.toDataObject(dataBaseObject));
        } else {
            throw new BizException("NO_PRODUCT_ITEM_FOUND","该产品数据监测项不存在!");
        }
    }

    @Override
    public void deleteProductDataItemById(Integer id) {
        ProductDataItemDO dataObject = productDataItemMapper.getProductDataItemById(id);
        if(null != dataObject){
            productDataItemMapper.deleteDataItemById(id);
        } else {
            throw new BizException("NO_PRODUCT_ITEM_FOUND","该产品数据监测项不存在!");
        }
    }

    @Override
    public void addExtraProductDataItem(ExtraProductDataItem extraProductDataItem) {
        calculationFormulaFilter(extraProductDataItem.getModelId(), extraProductDataItem.getNeedTransform(),
                extraProductDataItem.getCalculationFormula(), extraProductDataItem.getPlaceholder());
        ExtraProductDataItemDO dataObject = ExtraProductDataItemConvertor.toDataObject(extraProductDataItem);
        extraProductDataItemMapper.saveExtraProductDataItem(dataObject);
    }

    private void calculationFormulaFilter(Integer modelId, Boolean needTransform, String calculationFormula, String placeholder) {
        if(null == productModelMapper.getProductModelById(modelId)){
            throw new BizException("NO_PRODUCT_MODEL_FOUND","该产品型号不存在!");
        }
        if (needTransform){
            if (StringUtils.isEmpty(calculationFormula)){
                throw new BizException("CALCULATION_FORMULA_NOT_FOUND","计算公式不存在!");
            }
            if (StringUtils.isEmpty(placeholder)){
                throw new BizException("PLACEHOLDER_NOT_FOUND","占位符不存在!");
            }
        }
    }

    @Override
    public void updateExtraProductDataItem(ExtraProductDataItem extraProductDataItem) {
        ExtraProductDataItemDO dataObject = extraProductDataItemMapper.getExtraProductDataItemById(extraProductDataItem.getId());
        if(null != dataObject){
            ExtraProductDataItem dataBaseObject = ExtraProductDataItemConvertor.toDomain(dataObject);
            ObjectConvertUtils.copyProperties(extraProductDataItem,dataBaseObject);
            extraProductDataItemMapper.updateExtraProductDataItem(ExtraProductDataItemConvertor.toDataObject(dataBaseObject));
        } else {
            throw new BizException("NO_PRODUCT_EXTRA_ITEM_FOUND","该产品配置项不存在!");
        }
    }

    @Override
    public void deleteExtraProductDataItemById(Integer id) {
        ExtraProductDataItemDO dataObject = extraProductDataItemMapper.getExtraProductDataItemById(id);
        if(null != dataObject){
            extraProductDataItemMapper.deleteExtraProductDataItemById(id);
        } else {
            throw new BizException("NO_PRODUCT_EXTRA_ITEM_FOUND","该产品配置项不存在!");
        }
    }

    @Override
    public List<ProductDataModel> getDataModelByWebId(Integer webModelId) {
        List<DataModelDO> dataModels = dataModelMapper.getDataModelByWebId(webModelId);
        return DataModelConvertor.toDomainArray(dataModels);
    }

    @Transactional(rollbackFor =RuntimeException.class)
    @Override
    public void addProductDataComputationModel(DataComputationModel dataComputationModel) {
        Assert.isFalse(dataComputationModel.checkDataComputationIsNotLegal(),"DATA_COMPUTATION_MODEL_NOT_LEGAL","计算模型配置不合规!");
        ProductDataItemDO dataItem = productDataItemMapper.getProductDataItemById(dataComputationModel.getDataItemId());
        Assert.notNull(dataItem.getItemKind(),"选中的监测相位类型不能为空!");
        if(!DataItemSourceKind.CALCULATION_DATA.getType().equals(dataItem.getItemKind())){
            throw new BizException("PRODUCT_DATA_ITEM_NOT_TRUE","请正确选择数据检测项类型");
        }
        DataComputationModelDO dataObject = DataComputationModelConvertor.toDataObject(dataComputationModel);
        dataComputationModelMapper.saveDataComputationModel(dataObject);
        saveDataComputationItem(dataComputationModel, dataObject.getId());
    }

    @Transactional(rollbackFor =RuntimeException.class)
    @Override
    public void updateProductDataComputationModel(DataComputationModel dataComputationModel) {
        DataComputationModel dataBaseObject = getDataComputationModelById(dataComputationModel.getId());
        if(null != dataBaseObject){
            ObjectConvertUtils.copyProperties(dataComputationModel,dataBaseObject);
            dataComputationModelMapper.updateDataComputationModel(DataComputationModelConvertor.toDataObject(dataBaseObject));
        } else {
            throw new BizException("DATA_COMPUTATION_MODEL_NOT_FOUND","该计算模型配置不存在!");
        }
        dataComputationModelMapper.deleteDataComputationWithDataItem(dataComputationModel.getId());
        dataComputationModelMapper.deleteDataComputationWithExtraDataItem(dataComputationModel.getId());
        saveDataComputationItem(dataComputationModel, dataComputationModel.getId());
    }

    private void saveDataComputationItem(DataComputationModel dataComputationModel, Integer dataComputationId) {
        if (null != dataComputationModel.getDataComputationItems()){
            for (DataComputationItem computationItem : dataComputationModel.getDataComputationItems()){
                if(computationItem.getItemKind()==0){
                    if (null != extraProductDataItemMapper.getExtraProductDataItemById(computationItem.getCurrentId())){
                        dataComputationModelMapper.saveDataComputationWithExtraDataItem(dataComputationId,computationItem.getCurrentId(),computationItem.getPrefix());
                    } else {
                        throw new BizException("EXTRA_PRODUCT_ITEM_ERROR","当前产品配置项不存在");
                    }
                } else {
                    if (null != productDataItemMapper.getProductDataItemById(computationItem.getCurrentId())){
                        dataComputationModelMapper.saveDataComputationWithDataItem(dataComputationId,computationItem.getCurrentId(),computationItem.getPrefix());
                    } else {
                        throw new BizException("PRODUCT_ITEM_ERROR","当前数据项不存在");
                    }
                }
            }
        }
    }

    @Override
    public void deleteProductDataComputationModelById(Integer id) {
        DataComputationModelDO dataObject = dataComputationModelMapper.getDataComputationModelById(id);
        if (null != dataObject){
            dataComputationModelMapper.deleteDataComputationModelById(id);
        }
    }

    @Override
    public DataComputationModel getDataComputationModelById(Integer id) {
        DataComputationModelDO dataObject = dataComputationModelMapper.getDataComputationModelById(id);
        ProductDataItemDO productDataItem = productDataItemMapper.getProductDataItemById(dataObject.getDataItemId());
        DataComputationModel domainObject = DataComputationModelConvertor.toDomain(dataObject);
        domainObject.setProductDataItem(ProductDataItemConvertor.toDomain(productDataItem));
        domainObject.setComputationDataId(productDataItem.getDataId());
        List<DataComputationItem> computationItems = new ArrayList<>();
        List<DataComputationItemDO>  dataComputationItems = dataComputationModelMapper.queryDataComputationWithDataItem(id);
        List<DataComputationItemDO>  extraDataComputationItems = dataComputationModelMapper.queryDataComputationWithExtraDataItem(id);
        dataComputationItems.forEach(dataComputationItem -> {
            DataComputationItem current = new DataComputationItem();
            current.setCurrentId(dataComputationItem.getCurrentId());
            current.setItemKind(1);
            current.setPrefix(dataComputationItem.getPrefix());
            computationItems.add(current);
        });
        extraDataComputationItems.forEach(dataComputationItem -> {
            DataComputationItem current = new DataComputationItem();
            current.setCurrentId(dataComputationItem.getCurrentId());
            current.setItemKind(0);
            current.setPrefix(dataComputationItem.getPrefix());
            computationItems.add(current);
        });
        domainObject.setDataComputationItems(computationItems);
        return domainObject;
    }
}
