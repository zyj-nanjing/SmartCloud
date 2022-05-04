package www.bwsensing.com.device.service;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageInfo;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.api.ExtraProductDataItemService;
import www.bwsensing.com.device.convertor.ExtraProductDataItemCoConvertor;
import www.bwsensing.com.device.dto.clientobject.ExtraProductDataItemCO;
import www.bwsensing.com.device.dto.command.ProductExtraDataItemSaveCmd;
import www.bwsensing.com.device.dto.command.ProductExtraDataItemUpdateCmd;
import www.bwsensing.com.device.dto.command.query.ProductExtraDataItemPageQuery;
import www.bwsensing.com.device.gatewayimpl.database.ExtraProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ExtraProductDataItemDO;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import www.bwsensing.com.domain.device.model.ExtraProductDataItem;


/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class IExtraProductDataItemServiceImpl implements ExtraProductDataItemService {
    @Resource
    private ProductModelGateway productModelGateway;
    @Resource
    private ExtraProductDataItemMapper extraProductDataItemMapper;

    @Override
    public MultiResponse<ExtraProductDataItemCO> getProductExtraDataItemsByModelId(Integer modelId) {
        List<ExtraProductDataItemDO> dataItems = extraProductDataItemMapper.getExtraDataItemDataByModelId(modelId);
        return MultiResponse.of(ExtraProductDataItemCoConvertor.toClientCollections(dataItems));
    }

    @Override
    public PageResponse<ExtraProductDataItemCO> getProductExtraDataItemsByPage(ProductExtraDataItemPageQuery pageQuery) {
        PageHelperUtils<ProductExtraDataItemPageQuery, ExtraProductDataItemDO> pageHelper =
                PageHelperUtils.<ProductExtraDataItemPageQuery,ExtraProductDataItemDO>builder()
                        .pageFunction((groupQuery)->extraProductDataItemMapper.getExtraDataItemDataByModelId(pageQuery.getModelId())).build();
        PageInfo<ExtraProductDataItemDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<ExtraProductDataItemCO> result = ExtraProductDataItemCoConvertor.toClientCollections(pageInfo.getList());
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public SingleResponse<ExtraProductDataItemCO> getProductExtraDataItemById(Integer id) {
        ExtraProductDataItemDO dataObject = extraProductDataItemMapper.getExtraProductDataItemById(id);
        if (null != dataObject){
            return SingleResponse.of(ExtraProductDataItemCoConvertor.toClientObject(dataObject));
        }
        throw new BizException("PRODUCT_EXTRA_ITEM_NOT_EXIST","产品额外数据项不存在!");
    }

    @Override
    public Response addProductDataItem(ProductExtraDataItemSaveCmd saveCmd) {
        ExtraProductDataItem domainObject = new ExtraProductDataItem();
        BeanUtils.copyProperties(saveCmd, domainObject);
        productModelGateway.addExtraProductDataItem(domainObject);
        return Response.buildSuccess();
    }

    @Override
    public Response updateExtraProductDataItem(ProductExtraDataItemUpdateCmd updateCmd) {
        ExtraProductDataItem domainObject = new ExtraProductDataItem();
        BeanUtils.copyProperties(updateCmd, domainObject);
        productModelGateway.updateExtraProductDataItem(domainObject);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteExtraProductDataItemById(Integer id) {
        productModelGateway.deleteExtraProductDataItemById(id);
        return Response.buildSuccess();
    }
}
