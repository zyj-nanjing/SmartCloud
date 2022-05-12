package www.bwsensing.com.device.service;

import javax.annotation.Resource;

import com.alibaba.cola.dto.Response;
import com.github.pagehelper.PageInfo;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.convertor.*;
import org.springframework.stereotype.Service;
import com.alibaba.cola.catchlog.CatchAndLog;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.api.DataComputationModelService;
import www.bwsensing.com.device.dto.command.DataComputationItemCmd;
import www.bwsensing.com.device.dto.clientobject.DataComputationModelCO;
import www.bwsensing.com.device.dto.command.query.DataComputationModelPageQuery;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataComputationModelMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataComputationItemDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataComputationModelDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import www.bwsensing.com.device.dto.command.DataComputationModelSaveCmd;
import www.bwsensing.com.device.dto.command.DataComputationModelUpdateCmd;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.domain.device.model.data.model.DataComputationItem;
import www.bwsensing.com.domain.device.model.data.model.DataComputationModel;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import java.util.ArrayList;
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
    private ProductDataComputationModelMapper dataComputationModelMapper;

    @Override
    public Response addDataComputationModel(DataComputationModelSaveCmd saveCmd) {
        DataComputationModelDO dataObject = new DataComputationModelDO();
        BeanUtils.copyProperties(saveCmd, dataObject);
        DataComputationModel domainObject = DataComputationModelConvertor.toDomain(dataObject);
        if (null != saveCmd.getDataComputationItems()){
            List<DataComputationItem> dataComputationItems = getDataComputationItems(saveCmd.getDataComputationItems());
            domainObject.setDataComputationItems(dataComputationItems);
        }
        productModelGateway.addProductDataComputationModel(domainObject);
        return Response.buildSuccess();
    }


    @Override
    public Response updateDataComputationModel(DataComputationModelUpdateCmd updateCmd) {
        DataComputationModelDO dataObject = new DataComputationModelDO();
        BeanUtils.copyProperties(updateCmd, dataObject);
        DataComputationModel domainObject = DataComputationModelConvertor.toDomain(dataObject);
        if (null != updateCmd.getDataComputationItems()){
            List<DataComputationItem> dataComputationItems = getDataComputationItems(updateCmd.getDataComputationItems());
            domainObject.setDataComputationItems(dataComputationItems);
        }
        productModelGateway.updateProductDataComputationModel(domainObject);
        return Response.buildSuccess();
    }

    private List<DataComputationItem> getDataComputationItems(List<DataComputationItemCmd> dataItems) {
        List<DataComputationItem> dataComputationItems = new ArrayList<>();
        dataItems.forEach(currencyItem -> {
            DataComputationItem computationItem = new DataComputationItem();
            BeanUtils.copyProperties(currencyItem,computationItem);
            dataComputationItems.add(computationItem);
        });
        return dataComputationItems;
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
            List<DataComputationItemDO> computationItems = new ArrayList<>();
            List<DataComputationItemDO>  dataComputationItems = dataComputationModelMapper.queryDataComputationWithDataItem(id);
            List<DataComputationItemDO>  extraDataComputationItems = dataComputationModelMapper.queryDataComputationWithExtraDataItem(id);
            dataComputationItems.forEach(dataComputationItem -> {
                dataComputationItem.setItemKind(1);
                computationItems.add(dataComputationItem);
            });
            extraDataComputationItems.forEach(dataComputationItem -> {
                dataComputationItem.setItemKind(0);
                computationItems.add(dataComputationItem);
            });
            dataObject.setDataComputationItems(computationItems);
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
