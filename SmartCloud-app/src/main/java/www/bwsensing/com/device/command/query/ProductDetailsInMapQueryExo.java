package www.bwsensing.com.device.command.query;

import java.util.*;
import javax.annotation.Resource;
import com.alibaba.cola.dto.MultiResponse;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.api.ITimeSeriesDataService;
import www.bwsensing.com.common.clientobject.TimeSeriesDataCO;
import www.bwsensing.com.domain.project.model.MonitorProject;
import www.bwsensing.com.common.convertor.DataItemsCoConvertor;
import www.bwsensing.com.device.dto.clientobject.ProductMapDetailCO;
import www.bwsensing.com.domain.project.gateway.ProjectMonitorGateway;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;


/**
 * @author macos-zyj
 */
@Component
public class ProductDetailsInMapQueryExo {
    @Resource
    private ProjectMonitorGateway projectMonitorGateway;
    @Resource
    private ProductDeviceMapper productDeviceMapper;
    @Resource
    private ProductDataItemMapper itemsMapper;
    @Resource
    private ITimeSeriesDataService timeSeriesService;

    public MultiResponse<ProductMapDetailCO> execute(){
        List<ProductMapDetailCO> productMapDetailCollection = new ArrayList<>();
        List<MonitorProject>  monitorProjects = projectMonitorGateway.selectProjectByPermission();
        monitorProjects.forEach(project ->{
            List<ProductDeviceDO> sensorArray = productDeviceMapper.selectSensorByProjectId(project.getId());
            sensorArray.forEach(dataObject -> productMapDetailCollection.add(getProductMapDetail(project,dataObject)));
        });
        return MultiResponse.of(productMapDetailCollection);
    }


    private ProductMapDetailCO getProductMapDetail(MonitorProject project, ProductDeviceDO dataObject){
        ProductMapDetailCO mapDataObject = new ProductMapDetailCO();
        mapDataObject.setProjectId(project.getId());
        mapDataObject.setProjectName(project.getName());
        mapDataObject.setDeviceName(dataObject.getName());
        mapDataObject.setLongitude(dataObject.getLongitude());
        mapDataObject.setLatitude(dataObject.getLatitude());
        List<ProductDataItemDO> itemData = itemsMapper.selectItemsByModelId(dataObject.getModelId());
        Map<String, String> dataMap = new LinkedHashMap<>();
        itemData.forEach(item ->{
            TimeSeriesDataCO data = timeSeriesService.getLastStatisticsData(dataObject.getUniqueCode(),item.getDataId());
            dataMap.put(item.getDataId(),null !=data && null !=data.getDataValue() ? data.getDataValue().toString():"" );
        });
        mapDataObject.setDeviceCurrentData(dataMap);
        mapDataObject.setDataItems(DataItemsCoConvertor.toClientCollection(itemData));
        return mapDataObject;
    }
}
