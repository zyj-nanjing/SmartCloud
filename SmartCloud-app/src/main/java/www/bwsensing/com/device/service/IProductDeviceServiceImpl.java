package www.bwsensing.com.device.service;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.SysException;
import org.springframework.stereotype.Service;
import com.alibaba.cola.catchlog.CatchAndLog;
import www.bwsensing.com.common.utills.RSAUtils;
import www.bwsensing.com.device.dto.clientobject.*;
import www.bwsensing.com.device.api.ProductDeviceService;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.project.api.ProjectMemberService;
import www.bwsensing.com.device.command.ProductSaveCmdExo;
import www.bwsensing.com.device.command.ProductUpdateCmdExo;
import www.bwsensing.com.common.api.ITimeSeriesDataService;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.device.convertor.ProductDataItemCoConvertor;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.MonitorReceiveDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import static www.bwsensing.com.common.convertor.DataItemsCoConvertor.toClientObject;
import www.bwsensing.com.device.dto.command.query.FacilityReceivePageQuery;
import www.bwsensing.com.device.command.query.ProductDetailsInMapQueryExo;
import www.bwsensing.com.device.gatewayimpl.database.MonitorReceiveMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.device.dto.command.ProjectBindWithProductCmd;
import www.bwsensing.com.device.convertor.FacilityReceiveCoConvertor;
import www.bwsensing.com.device.dto.command.query.ProductSortQuery;
import www.bwsensing.com.device.command.query.SensorSortQueryExo;
import www.bwsensing.com.device.convertor.ProductCoConvertor;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.project.model.ProjectRoleEnum;
import www.bwsensing.com.device.dto.command.ProductSaveCmd;
import www.bwsensing.com.device.dto.command.ProductUpdateCmd;
import www.bwsensing.com.domain.system.model.token.TokenData;
import com.alibaba.cola.dto.Response;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;



/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class IProductDeviceServiceImpl implements ProductDeviceService {
    @Resource
    private SensorSortQueryExo sortQueryExo;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private SystemUserGateway userGateway;
    @Resource
    private ProjectMemberService projectMemberService;
    @Resource
    private ProductDeviceMapper productDeviceMapper;
    @Resource
    private ProductDeviceGateway productDeviceGateway;
    @Resource
    private ProductDetailsInMapQueryExo productDetailsInMapQueryExo;
    @Resource
    private ProductSaveCmdExo productSaveCmdExo;
    @Resource
    private ProductUpdateCmdExo productUpdateCmdExo;
    @Resource
    private MonitorReceiveMapper monitorReceiveMapper;
    @Resource
    private ProductDataItemMapper itemsMapper;
    @Resource
    private ITimeSeriesDataService timeSeriesService;
    @Resource
    private ProductDataItemMapper productDataItemMapper;


    @Override
    public PageResponse<ProductDeviceCO> queryProductByPageSort(ProductSortQuery sortQuery) {
        return sortQueryExo.execute(sortQuery);
    }

    @Override
    public MultiResponse<ProductDeviceCO> queryProductsBySort(ProductSortQuery sortQuery) {
        return sortQueryExo.executeTotal(sortQuery);
    }

    @Override
    public PageResponse<FacilityReceiveCO> queryFacilitySendsByUniqueCode(FacilityReceivePageQuery pageQuery) {
        PageHelperUtils<FacilityReceivePageQuery, MonitorReceiveDO> pageHelper =
                PageHelperUtils.<FacilityReceivePageQuery,MonitorReceiveDO>builder()
                        .pageFunction((groupQuery)->monitorReceiveMapper.selectMonitorReceiveBySort(initializeQuery(groupQuery))).build();
        PageInfo<MonitorReceiveDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<FacilityReceiveCO> result = FacilityReceiveCoConvertor.toClientObjectList(pageInfo.getList());
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public SingleResponse<String> getProductCurrentData() {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser systemUser = userGateway.getUserInfoContainRole(tokenData.getUserId());
        List<ProductDeviceDO> sensorList = productDeviceMapper.selectSensorByGroupId(systemUser.getGroupId());
        List<ProductApiCO> results = getSensorDataCollection(sensorList);
        String jsonString = JSONArray.parseArray(JSON.toJSONString(results)).toString();
        Assert.notNull(systemUser.getPublicKey(),"当前用户的公钥为生成!");
        try {
            String decryptedData = RSAUtils.encryptHexByPublicKey(jsonString.getBytes(),systemUser.getPublicKey());
            return SingleResponse.of(decryptedData);
        } catch (Exception  ex){
            ex.printStackTrace();
            throw new SysException("加密失败!");
        }
    }


    private List<ProductApiCO> getSensorDataCollection(List<ProductDeviceDO> dataCollection){
        List<ProductApiCO> sensorDataCollection = new ArrayList<>();
        dataCollection.forEach(currentSensor -> sensorDataCollection.add(toApiDataFormat(currentSensor)));
        return sensorDataCollection;
    }

    private ProductApiCO toApiDataFormat(ProductDeviceDO currentSensor){
        ProductApiCO apiData = new ProductApiCO();
        apiData.setUniqueCode(currentSensor.getUniqueCode());
        apiData.setName(currentSensor.getName());
        apiData.setDataItems(new ArrayList<>());
        apiData.setDataMap(new LinkedHashMap<>());
        List<ProductDataItemDO> itemData = itemsMapper.selectItemsByModelId(currentSensor.getModelId());
        itemData.forEach(item ->{
            apiData.getDataItems().add(toClientObject(item));
            apiData.getDataMap().put(item.getDataId(), timeSeriesService.getLastStatisticsData(currentSensor.getUniqueCode(),item.getDataId()));
        });
        return apiData;
    }

    private MonitorReceiveDO initializeQuery(FacilityReceivePageQuery pageQuery){
        MonitorReceiveDO querySortQuery = new MonitorReceiveDO();
        querySortQuery.setUniqueCode(pageQuery.getUniqueCode());
        return querySortQuery;
    }

    @Override
    public SingleResponse<ProductDeviceCO> queryProductDetailById(Integer id) {
        ProductDeviceDO queryData = productDeviceMapper.getProductDetailById(id);
        if(null != queryData) {
            return SingleResponse.of(ProductCoConvertor.toClientObject(queryData));
        }
        return SingleResponse.of(new ProductDeviceCO());
    }

    @Override
    public MultiResponse<ProductDeviceCO> queryProductsByProjectId(Integer projectId) {
        List<ProductDeviceDO> sensorList = productDeviceMapper.selectSensorByProjectId(projectId);
        return MultiResponse.of(ProductCoConvertor.toClientObjectArray(sensorList));
    }

    @Override
    public MultiResponse<ProductMapDetailCO> getProductsWithIndexMap() {
        return productDetailsInMapQueryExo.execute();
    }

    @Override
    public Response saveProduct(ProductSaveCmd saveCmd) {
        return productSaveCmdExo.execute(saveCmd);
    }

    @Override
    public Response updateProduct(ProductUpdateCmd updateCmd) {
        return productUpdateCmdExo.execute(updateCmd);
    }

    @Override
    public Response productBindWithProject(ProjectBindWithProductCmd projectBindCmd) {
        String roleCode = projectMemberService.getCurrentProjectAuthCode(projectBindCmd.getProjectId());
        if(roleCode .equals(ProjectRoleEnum.PROJECT_VIEWER.getRoleCode())){
            throw new BizException("NO_PERMISSION_TO_ACT","无权限绑定传感器");
        }
        List<ProductBindStatusCO> clientObjects = getProductBindStatusWithPositionId(projectBindCmd.getProjectId()).getData();
        productDeviceMapper.deleteProductBind(projectBindCmd.getProjectId());
        projectBindCmd.getSensorIds().forEach(productId ->{
            if(null != clientObjects&& clientObjects.size() >0){
                clientObjects.forEach(client ->{
                    if(client .getSensorId().equals(productId)){
                        productDeviceMapper.bindProject(productId,projectBindCmd.getProjectId());
                    }
                });
            }
        });
        return Response.buildSuccess();
    }

    @Override
    public Response deleteProduct(Integer id) {
        productDeviceGateway.deleteProductDeviceById(id);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<ProductBindStatusCO> getProductBindStatusWithPositionId(Integer positionId) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        List<ProductDeviceDO> sensorList = productDeviceMapper.selectSensorBindByPosition(positionId,tokenData.getGroupId());
        List<ProductBindStatusCO> bindArray = new ArrayList<>(sensorList.size());
        sensorList.forEach(sensorInfo -> {
            ProductBindStatusCO bindCo = new ProductBindStatusCO();
            bindCo.setSensorId(sensorInfo.getId());
            bindCo.setSensorName(sensorInfo.getName());
            bindCo.setSelect(sensorInfo.getPositionId() != null);
            bindArray.add(bindCo);
        });
        return MultiResponse.of(bindArray);
    }
    @Override
    public MultiResponse<ProductDataItemCO> getProductDataItemsByUniqueCode(String uniqueCode){
        ProductDeviceDO queryData = productDeviceMapper.getProductDetailByUniqueCode(uniqueCode);
        if(null != queryData) {
            List<ProductDataItemDO> dataItems = productDataItemMapper.selectItemsByModelId(queryData.getModelId());
            return MultiResponse.of(ProductDataItemCoConvertor.toClientCollection(dataItems));
        }
        return MultiResponse.of(new ArrayList<>());
    }
}
