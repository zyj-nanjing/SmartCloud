package www.bwsensing.com.device.command.query;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.convertor.ProductCoConvertor;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.device.dto.command.query.ProductSortQuery;
import www.bwsensing.com.device.dto.clientobject.ProductDeviceCO;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class SensorSortQueryExo {
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private ProductDeviceMapper productDeviceMapper;

    public PageResponse<ProductDeviceCO> execute(ProductSortQuery sortQuery){
        PageHelperUtils<ProductSortQuery, ProductDeviceDO> pageHelper =
                PageHelperUtils.<ProductSortQuery, ProductDeviceDO>builder()
                        .pageFunction((query)-> productDeviceMapper.selectProductDeviceBySort(initSensorQuery(query))).build();
        PageInfo<ProductDeviceDO> page= pageHelper.getPageCollections(sortQuery);
        List<ProductDeviceCO> result = ProductCoConvertor.toClientObjectArray(page.getList());
        return PageResponse.of(result,(int)page.getTotal(),page.getPageSize(),sortQuery.getPageIndex());
    }

    public MultiResponse<ProductDeviceCO> executeTotal(ProductSortQuery sortQuery){
        List<ProductDeviceDO> sensorCollection= productDeviceMapper.selectProductDeviceBySort(initSensorQuery(sortQuery));
        List<ProductDeviceCO> result = ProductCoConvertor.toClientObjectArray(sensorCollection);
        return MultiResponse.of(result);
    }

    private ProductDeviceDO initSensorQuery(ProductSortQuery sortQuery){
        ProductDeviceDO query = new ProductDeviceDO();
        query.setOrganizationId(tokenGateway.getTokenInfo().getGroupId());
        query.setName(sortQuery.getName());
        query.setProjectId(sortQuery.getProjectId());
        query.setModelId(sortQuery.getModelId());
        return query;
    }
}
