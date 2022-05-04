package www.bwsensing.com.device.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.api.ProductDataItemService;
import www.bwsensing.com.device.convertor.ProductDataItemCoConvertor;
import www.bwsensing.com.device.dto.clientobject.ProductDataItemCO;
import www.bwsensing.com.device.dto.command.ProductDataItemSaveCmd;
import www.bwsensing.com.device.dto.command.ProductDataItemUpdateCmd;
import www.bwsensing.com.device.dto.command.query.ProductDataItemPageQuery;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import www.bwsensing.com.domain.device.model.DataItemAttribute;
import www.bwsensing.com.domain.device.model.DataItemSourceKind;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import javax.annotation.Resource;
import java.util.List;


/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class IProductDataItemServiceImpl implements ProductDataItemService {
    @Resource
    private ProductDataItemMapper productDataItemMapper;
    @Resource
    private ProductModelGateway productModelGateway;

    @Override
    public MultiResponse<ProductDataItemCO> getProductDataItemsByModelId(Integer modelId) {
        List<ProductDataItemDO> dataItems = productDataItemMapper.selectItemsByModelId(modelId);
        return MultiResponse.of(ProductDataItemCoConvertor.toClientCollection(dataItems));
    }

    @Override
    public PageResponse<ProductDataItemCO> getProductDataItemsByPage(ProductDataItemPageQuery pageQuery) {
        PageHelperUtils<ProductDataItemPageQuery,ProductDataItemDO> pageHelper =
                PageHelperUtils.<ProductDataItemPageQuery,ProductDataItemDO>builder()
                        .pageFunction((groupQuery)->productDataItemMapper.selectItemsByModelId(pageQuery.getModelId())).build();
        PageInfo<ProductDataItemDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<ProductDataItemCO> result = ProductDataItemCoConvertor.toClientCollection(pageInfo.getList());
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public SingleResponse<ProductDataItemCO> getProductDataItemById(Integer id) {
        ProductDataItemDO dataObject = productDataItemMapper.getProductDataItemById(id);
        if (null != dataObject){
            return SingleResponse.of(ProductDataItemCoConvertor.toClientObject(dataObject));
        }
        throw new BizException("PRODUCT_ITEM_NOT_EXIST","产品监测项不存在!");
    }

    @Override
    public Response addProductDataItem(ProductDataItemSaveCmd saveCmd) {
        ProductDataItem domainObject = new ProductDataItem();
        BeanUtils.copyProperties(saveCmd, domainObject);
        domainObject.setItemAttribute(DataItemAttribute.getDataItemAttribute(saveCmd.getItemAttribute()));
        domainObject.setItemKind(DataItemSourceKind.getItemSourceKind(saveCmd.getItemKind()));
        productModelGateway.addProductDataItem(domainObject);
        return Response.buildSuccess();
    }

    @Override
    public Response updateProductDataItem(ProductDataItemUpdateCmd updateCmd) {
        ProductDataItem domainObject = new ProductDataItem();
        BeanUtils.copyProperties(updateCmd, domainObject);
        productModelGateway.updateProductDataItem(domainObject);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteProductDataItemById(Integer id) {
        productModelGateway.deleteProductDataItemById(id);
        return Response.buildSuccess();
    }
}
