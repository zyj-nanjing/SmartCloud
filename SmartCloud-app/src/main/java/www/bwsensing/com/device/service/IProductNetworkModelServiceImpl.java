package www.bwsensing.com.device.service;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.xxl.mq.client.message.XxlMqMessage;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import com.xxl.mq.client.producer.XxlMqProducer;
import www.bwsensing.com.common.utills.ObjectConvertUtils;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.api.ProductNetworkModelService;
import www.bwsensing.com.device.dto.clientobject.ProductNetworkModelCO;
import www.bwsensing.com.device.dto.command.ProductNetworkModelSaveCmd;
import www.bwsensing.com.device.gatewayimpl.database.ProductModelMapper;
import www.bwsensing.com.device.dto.command.ProductNetworkModelUpdateCmd;
import www.bwsensing.com.device.convertor.ProductNetworkModelCoConvertor;
import www.bwsensing.com.device.dto.command.query.ProductNetworkModelPageQuery;
import www.bwsensing.com.device.gatewayimpl.database.ProductNetworkModelMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductNetworkModelDO;



/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class IProductNetworkModelServiceImpl implements ProductNetworkModelService {
    private static final String CLIENT_TOPIC = "collection_network_model";

    @Resource
    private ProductNetworkModelMapper networkModelMapper;

    @Resource
    private ProductModelMapper productModelMapper;

    @Override
    public Response addNetworkModel(ProductNetworkModelSaveCmd saveCmd) {
        if(null == productModelMapper.getProductModelById(saveCmd.getModelId())){
            throw new BizException("NO_PRODUCT_MODEL_FOUND","该产品型号不存在!");
        }
        ProductNetworkModelDO dataObject = new ProductNetworkModelDO();
        BeanUtils.copyProperties(saveCmd,dataObject);
        networkModelMapper.saveNetworkModel(dataObject);
        XxlMqProducer.broadcast(new XxlMqMessage(CLIENT_TOPIC, ""));
        return Response.buildSuccess();
    }

    @Override
    public Response updateNetworkModel(ProductNetworkModelUpdateCmd updateCmd) {
        ProductNetworkModelDO dataObject = networkModelMapper.getNetworkModelById(updateCmd.getId());
        if(null != dataObject){
            ProductNetworkModelDO dataBaseObject = new ProductNetworkModelDO();
            BeanUtils.copyProperties(updateCmd,dataBaseObject);
            ObjectConvertUtils.copyProperties(dataBaseObject,dataObject);
            networkModelMapper.updateNetworkModel(dataBaseObject);
        } else {
            throw new BizException("NO_NETWORK_MODEL_FOUND","该产品网络模型不存在!");
        }
        XxlMqProducer.broadcast(new XxlMqMessage(CLIENT_TOPIC, ""));
        return Response.buildSuccess();
    }

    @Override
    public Response deleteNetworkModelById(Integer id) {
        ProductNetworkModelDO dataObject = networkModelMapper.getNetworkModelById(id);
        if(null == dataObject){
            throw new BizException("NO_NETWORK_MODEL_FOUND","该产品网络模型不存在!");
        }
        networkModelMapper.deleteNetworkModelById(id);
        XxlMqProducer.broadcast(new XxlMqMessage(CLIENT_TOPIC, ""));
        return Response.buildSuccess();
    }

    @Override
    public PageResponse<ProductNetworkModelCO> queryProductModelNetworkBySort(ProductNetworkModelPageQuery pageQuery) {
        PageHelperUtils<ProductNetworkModelPageQuery, ProductNetworkModelDO> pageHelper =
                PageHelperUtils.<ProductNetworkModelPageQuery, ProductNetworkModelDO>builder()
                        .pageFunction((groupQuery)->networkModelMapper.queryNetworkModelBySort (initializeQuery(groupQuery))).build();
        PageInfo<ProductNetworkModelDO> page= pageHelper.getPageCollections(pageQuery);
        List<ProductNetworkModelCO> result = ProductNetworkModelCoConvertor.toClientCollection(page.getList());
        return PageResponse.of(result, (int)page.getTotal(),page.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public SingleResponse<ProductNetworkModelCO> getNetworkModelById(Integer id) {
        ProductNetworkModelDO dataObject = networkModelMapper.getNetworkModelById(id);
        if(null == dataObject){
            throw new BizException("NO_NETWORK_MODEL_FOUND","该产品网络模型不存在!");
        } else {
            return SingleResponse.of(ProductNetworkModelCoConvertor.toClientObject(dataObject));
        }
    }

    private ProductNetworkModelDO initializeQuery(ProductNetworkModelPageQuery pageQuery){
        ProductNetworkModelDO query = new ProductNetworkModelDO();
        BeanUtils.copyProperties(pageQuery,query);
        return query;
    }

}
