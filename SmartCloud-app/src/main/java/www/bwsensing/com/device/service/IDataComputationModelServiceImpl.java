package www.bwsensing.com.device.service;

import javax.annotation.Resource;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.api.DataComputationModelService;
import www.bwsensing.com.device.convertor.*;
import www.bwsensing.com.device.dto.clientobject.DataComputationModelCO;
import www.bwsensing.com.device.dto.clientobject.ExtraProductDataItemCO;
import www.bwsensing.com.device.dto.command.query.DataComputationModelPageQuery;
import www.bwsensing.com.device.dto.command.query.ProductExtraDataItemPageQuery;
import www.bwsensing.com.device.gatewayimpl.database.ExtraProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataComputationModelMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataComputationModelDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ExtraProductDataItemDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import www.bwsensing.com.device.dto.command.DataComputationModelSaveCmd;
import www.bwsensing.com.device.dto.command.DataComputationModelUpdateCmd;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import www.bwsensing.com.domain.device.model.data.model.DataComputationModel;

import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class IDataComputationModelServiceImpl implements DataComputationModelService {

    @Resource
    private ProductModelGateway productModelGateway;
    @Resource
    private ProductDataItemMapper productDataItemMapper;
    @Resource
    private ExtraProductDataItemMapper extraProductDataItemMapper;
    @Resource
    private ProductDataComputationModelMapper dataComputationModelMapper;

    @Override
    public Response addDataComputationModel(DataComputationModelSaveCmd saveCmd) {
        DataComputationModel domainObject = new DataComputationModel();
        BeanUtils.copyProperties(saveCmd, domainObject);
        if (null != saveCmd.getProductDataItems()){
            List<ProductDataItemDO> productDataItems = productDataItemMapper.queryProductDataItemByIds(saveCmd.getProductDataItems());
            domainObject.setProductDataItems(ProductDataItemConvertor.toDomainCollection(productDataItems));
        }
        if (null != saveCmd.getExtraProductDataItems()){
            List<ExtraProductDataItemDO> extraProductDataItems = extraProductDataItemMapper.getExtraDataItemDataByIds(saveCmd.getExtraProductDataItems());
                    domainObject.setExtraProductDataItems(ExtraProductDataItemConvertor.toDomainCollection(extraProductDataItems));
        }
        productModelGateway.addProductDataComputationModel(domainObject);
        return Response.buildSuccess();
    }

    @Override
    public Response updateDataComputationModel(DataComputationModelUpdateCmd updateCmd) {
        DataComputationModel domainObject = new DataComputationModel();
        BeanUtils.copyProperties(updateCmd, domainObject);
        if (null != updateCmd.getProductDataItems()){
            List<ProductDataItemDO> productDataItems = productDataItemMapper.queryProductDataItemByIds(updateCmd.getProductDataItems());
            domainObject.setProductDataItems(ProductDataItemConvertor.toDomainCollection(productDataItems));
        }
        if (null != updateCmd.getExtraProductDataItems()){
            List<ExtraProductDataItemDO> extraProductDataItems = extraProductDataItemMapper.getExtraDataItemDataByIds(updateCmd.getExtraProductDataItems());
            domainObject.setExtraProductDataItems(ExtraProductDataItemConvertor.toDomainCollection(extraProductDataItems));
        }
        productModelGateway.updateProductDataComputationModel(domainObject);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteDataComputationModel(Integer id) {
        productModelGateway.deleteProductDataComputationModelById(id);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<DataComputationModelCO> getDataComputationModelById(Integer id) {
        DataComputationModelDO dataObject = dataComputationModelMapper.getDataComputationModelById(id);
        if (null  != dataObject){
            ProductDataItemDO productDataItemDO = productDataItemMapper.getProductDataItemById(dataObject.getDataItemId());
            DataComputationModelCO clientObject = DataComputationModelCoConvertor.toClientObject(dataObject);
            if (null != productDataItemDO){
                clientObject.setProductDataItem(ProductDataItemCoConvertor.toClientObject(productDataItemDO));
                clientObject.setComputationDataId(productDataItemDO.getDataId());
            }
            return SingleResponse.of(clientObject);
        }
        return SingleResponse.of(new DataComputationModelCO());
    }

    @Override
    public PageResponse<DataComputationModelCO> getDataComputationModelBySort(DataComputationModelPageQuery pageQuery) {
        PageHelperUtils<DataComputationModelPageQuery, DataComputationModelDO> pageHelper =
                PageHelperUtils.<DataComputationModelPageQuery, DataComputationModelDO>builder()
                        .pageFunction((groupQuery)->dataComputationModelMapper.queryDataComputationModelBySort(initDataComputationMode(pageQuery))).build();
        PageInfo<DataComputationModelDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<DataComputationModelCO> result = DataComputationModelCoConvertor.toClientCollections(pageInfo.getList());
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }


    @Override
    public MultiResponse<DataComputationModelCO> getDataComputationModelByModelId(Integer modelId) {
        DataComputationModelDO dataObject = new DataComputationModelDO();
        dataObject.setModelId(modelId);
        List<DataComputationModelDO> dataCollection = dataComputationModelMapper.queryDataComputationModelBySort(dataObject);
        return MultiResponse.of(DataComputationModelCoConvertor.toClientCollections(dataCollection));
    }

    private DataComputationModelDO initDataComputationMode(DataComputationModelPageQuery pageQuery){
        DataComputationModelDO dataObject = new DataComputationModelDO();
        BeanUtils.copyProperties(pageQuery, dataObject);
        return dataObject;
    }
}
