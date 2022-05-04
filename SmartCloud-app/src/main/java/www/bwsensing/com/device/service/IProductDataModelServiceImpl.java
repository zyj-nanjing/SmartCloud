package www.bwsensing.com.device.service;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.github.pagehelper.PageInfo;
import com.alibaba.cola.dto.PageResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.api.ProductDataModelService;
import www.bwsensing.com.device.convertor.DataModelConvertor;
import www.bwsensing.com.device.convertor.DataModelItemConvertor;
import www.bwsensing.com.device.dto.command.ProductDataModelItemAddCmd;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import www.bwsensing.com.device.dto.command.ProductDataModelAddCmd;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelCO;
import www.bwsensing.com.device.dto.command.ProductDataModelItemUpdateCmd;
import www.bwsensing.com.device.convertor.ProductDataModelItemCoConvertor;
import www.bwsensing.com.device.gatewayimpl.database.DataModelMapper;
import www.bwsensing.com.device.convertor.ProductDataModelCoConvertor;
import www.bwsensing.com.device.dto.command.ProductDataModelUpdateCmd;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelItemCO;
import www.bwsensing.com.device.gatewayimpl.database.DataModelItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataModelDO;
import www.bwsensing.com.device.dto.command.query.ProductDataModelsPageQuery;
import www.bwsensing.com.device.dto.command.query.ProductDataModelItemsPageQuery;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataModelItemDO;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class IProductDataModelServiceImpl implements ProductDataModelService {

    @Resource
    private ProductModelGateway productModelGateway;

    @Resource
    private DataModelMapper dataModelMapper;

    @Resource
    private DataModelItemMapper dataModelItemMapper;

    @Override
    public Response addProductDateModel(ProductDataModelAddCmd addCmd) {
        DataModelDO dataModelDo = new DataModelDO();
        BeanUtils.copyProperties(addCmd, dataModelDo);
        productModelGateway.addProductDataModel(DataModelConvertor.toDomain(dataModelDo));
        return Response.buildSuccess();
    }

    @Override
    public Response updateProductDateModel(ProductDataModelUpdateCmd updateCmd) {
        DataModelDO dataModelDo = new DataModelDO();
        BeanUtils.copyProperties(updateCmd, dataModelDo);
        productModelGateway.updateProductDataModel(DataModelConvertor.toDomain(dataModelDo));
        return Response.buildSuccess();
    }

    @Override
    public Response deleteProductDateModel(Integer modelId) {
        productModelGateway.deleteProductDataModel(modelId);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<ProductDataModelCO> getProductDataModelById(Integer id) {
        DataModelDO dataModelDo = dataModelMapper.queryDataModelById(id);
        return SingleResponse.of(ProductDataModelCoConvertor.toClientObject(dataModelDo));
    }

    @Override
    public PageResponse<ProductDataModelCO> getProductDataModelsByPageQuery(ProductDataModelsPageQuery pageQuery) {
        PageHelperUtils<ProductDataModelsPageQuery, DataModelDO> pageHelper =
                PageHelperUtils.<ProductDataModelsPageQuery,DataModelDO>builder()
                        .pageFunction((groupQuery)->dataModelMapper.queryDataModelBySort (initializeQuery(groupQuery))).build();
        PageInfo<DataModelDO> page= pageHelper.getPageCollections(pageQuery);
        List<ProductDataModelCO> result = ProductDataModelCoConvertor.toClientCollection(page.getList());
        return PageResponse.of(result, (int)page.getTotal(),page.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public Response addProductDataItem(ProductDataModelItemAddCmd addCmd) {
        DataModelItemDO dataObject = new DataModelItemDO();
        BeanUtils.copyProperties(addCmd, dataObject);
        productModelGateway.addProductDataModelItem(DataModelItemConvertor.toDomain(dataObject));
        return Response.buildSuccess();
    }

    @Override
    public Response updateProductDataItem(ProductDataModelItemUpdateCmd updateCmd) {
        DataModelItemDO dataObject = new DataModelItemDO();
        BeanUtils.copyProperties(updateCmd, dataObject);
        productModelGateway.updateProductDataModelItem(DataModelItemConvertor.toDomain(dataObject));
        return Response.buildSuccess();
    }

    @Override
    public Response deleteProductDataItemById(Integer id) {
        productModelGateway.deleteProductDataModelItem(id);
        return Response.buildSuccess();
    }

    @Override
    public PageResponse<ProductDataModelItemCO> getProductDataModelItemsByPageQuery(ProductDataModelItemsPageQuery pageQuery) {
        PageHelperUtils<ProductDataModelItemsPageQuery, DataModelItemDO> pageHelper =
                PageHelperUtils.<ProductDataModelItemsPageQuery, DataModelItemDO>builder()
                        .pageFunction((groupQuery)->dataModelItemMapper.queryDataModelItemBySort (initializeQuery(groupQuery))).build();
        PageInfo<DataModelItemDO> page= pageHelper.getPageCollections(pageQuery);
        List<ProductDataModelItemCO> result = ProductDataModelItemCoConvertor.toClientCollection(page.getList());
        return PageResponse.of(result, (int)page.getTotal(),page.getPageSize(),pageQuery.getPageIndex());
    }


    @Override
    public SingleResponse<ProductDataModelItemCO> getProductDataModelItemById(Integer id){
        DataModelItemDO dataItemDO = dataModelItemMapper.queryDataModelItemById(id);
        return SingleResponse.of(ProductDataModelItemCoConvertor.toClientObject(dataItemDO));
    }

    private DataModelDO initializeQuery(ProductDataModelsPageQuery pageQuery){
        DataModelDO query = new DataModelDO();
        BeanUtils.copyProperties(pageQuery,query);
        return query;
    }

    private DataModelItemDO initializeQuery(ProductDataModelItemsPageQuery pageQuery){
        DataModelItemDO query = new DataModelItemDO();
        BeanUtils.copyProperties(pageQuery,query);
        return query;
    }


}
