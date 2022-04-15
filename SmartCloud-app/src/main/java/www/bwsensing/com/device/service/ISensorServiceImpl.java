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
import www.bwsensing.com.device.api.SensorService;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.project.api.ProjectMemberService;
import www.bwsensing.com.device.command.SensorSaveCmdExo;
import www.bwsensing.com.device.command.SensorUpdateCmdExo;
import www.bwsensing.com.common.api.ITimeSeriesDataService;
import www.bwsensing.com.common.clientobject.DataItemsCO;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.device.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorItemsDO;
import www.bwsensing.com.device.command.query.SensorInMapQueryExo;
import www.bwsensing.com.device.command.query.SensorSortQueryExo;
import www.bwsensing.com.device.convertor.FacilityReceiveCoConvertor;
import www.bwsensing.com.device.convertor.SensorCoConvertor;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.project.model.ProjectRoleEnum;
import www.bwsensing.com.device.dto.command.SensorProjectBindCmd;
import www.bwsensing.com.device.dto.command.SensorSaveCmd;
import www.bwsensing.com.device.dto.command.query.FacilityReceivePageQuery;
import www.bwsensing.com.device.dto.command.query.SensorSortQuery;
import www.bwsensing.com.device.dto.command.SensorUpdateCmd;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.device.gatewayimpl.database.MonitorReceiveMapper;
import www.bwsensing.com.device.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.MonitorReceiveDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorDO;
import com.alibaba.cola.dto.Response;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static www.bwsensing.com.common.convertor.DataItemsCoConvertor.toDataItemsCo;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class ISensorServiceImpl implements SensorService {
    @Resource
    private SensorSortQueryExo sortQueryExo;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private SystemUserGateway userGateway;
    @Resource
    private ProjectMemberService projectMemberService;
    @Resource
    private SensorMapper sensorMapper;
    @Resource
    private SensorInMapQueryExo sensorInMapQueryExo;
    @Resource
    private SensorSaveCmdExo sensorSaveCmdExo;
    @Resource
    private SensorUpdateCmdExo sensorUpdateCmdExo;
    @Resource
    private MonitorReceiveMapper monitorReceiveMapper;
    @Resource
    private MonitorItemsMapper itemsMapper;
    @Resource
    private ITimeSeriesDataService timeSeriesService;


    @Override
    public PageResponse<SensorCO> querySensorBySort(SensorSortQuery sortQuery) {
        return sortQueryExo.execute(sortQuery);
    }

    @Override
    public MultiResponse<SensorCO> querySensorAllBySort(SensorSortQuery sortQuery) {
        return sortQueryExo.executeTotal(sortQuery);
    }

    @Override
    public PageResponse<FacilityReceiveCO> queryFacilitySendsBySn(FacilityReceivePageQuery pageQuery) {
        PageHelperUtils<FacilityReceivePageQuery, MonitorReceiveDO> pageHelper =
                PageHelperUtils.<FacilityReceivePageQuery,MonitorReceiveDO>builder()
                        .pageFunction((groupQuery)->monitorReceiveMapper.selectMonitorReceiveBySort(initializeQuery(groupQuery))).build();
        PageInfo<MonitorReceiveDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<FacilityReceiveCO> result = FacilityReceiveCoConvertor.toClientObjectList(pageInfo.getList());
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public SingleResponse<String> getCurrentSensorData() {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser systemUser = userGateway.getUserInfoContainRole(tokenData.getUserId());
        List<SensorDO> sensorList = sensorMapper.selectSensorByGroupId(systemUser.getGroupId());
        List<SensorApiCO> results = getSensorDataCollection(sensorList);
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


    private List<SensorApiCO> getSensorDataCollection(List<SensorDO> dataCollection){
        List<SensorApiCO> sensorDataCollection = new ArrayList<>();
        dataCollection.forEach(currentSensor -> sensorDataCollection.add(toApiDataFormat(currentSensor)));
        return sensorDataCollection;
    }

    private SensorApiCO toApiDataFormat(SensorDO currentSensor){
        SensorApiCO apiData = new SensorApiCO();
        apiData.setUniqueCode(currentSensor.getSn());
        apiData.setName(currentSensor.getName());
        apiData.setModelName(currentSensor.getModelName());
        apiData.setDataItems(new ArrayList<>());
        apiData.setDataMap(new LinkedHashMap<>());
        List<MonitorItemsDO> itemData = itemsMapper.selectItemsByModelId(currentSensor.getModelId());
        itemData.forEach(item ->{
            apiData.getDataItems().add(toDataItemsCo(item));
            apiData.getDataMap().put(item.getDataId(), timeSeriesService.getLastStatisticsData(currentSensor.getSn(),item.getDataId()));
        });
        return apiData;
    }

    private MonitorReceiveDO initializeQuery(FacilityReceivePageQuery pageQuery){
        MonitorReceiveDO querySortQuery = new MonitorReceiveDO();
        querySortQuery.setSn(pageQuery.getSn());
        return querySortQuery;
    }

    @Override
    public SingleResponse<SensorCO> querySensorById(Integer id) {
        SensorDO queryData = sensorMapper.selectSensorById(id);
        return SingleResponse.of(SensorCoConvertor.toClientObject(queryData));
    }

    @Override
    public MultiResponse<SensorCO> selectSensorByProjectId(Integer projectId) {
        List<SensorDO> sensorList = sensorMapper.selectSensorByProjectId(projectId);
        return MultiResponse.of(SensorCoConvertor.toClientObjectArray(sensorList));
    }

    @Override
    public MultiResponse<SensorMapCO> showSensorInMap() {
        return sensorInMapQueryExo.execute();
    }

    @Override
    public Response saveSensor(SensorSaveCmd saveCmd) {
        return sensorSaveCmdExo.execute(saveCmd);
    }

    @Override
    public Response updateSensor(SensorUpdateCmd updateCmd) {
        return sensorUpdateCmdExo.execute(updateCmd);
    }

    @Override
    public Response bindSensorInProject(SensorProjectBindCmd projectBindCmd) {
        String roleCode = projectMemberService.getCurrentProjectAuthCode(projectBindCmd.getProjectId());
        if(roleCode .equals(ProjectRoleEnum.PROJECT_VIEWER.getRoleCode())){
            throw new BizException("NO_PERMISSION_TO_ACT","无权限绑定传感器");
        }
        List<SensorBindCO> clientObjects = selectSensorBindArray(projectBindCmd.getProjectId()).getData();
        sensorMapper.deleteSensorBind(projectBindCmd.getProjectId());
        projectBindCmd.getSensorIds().forEach(sensorId ->{
            if(null != clientObjects&& clientObjects.size() >0){
                clientObjects.forEach(client ->{
                    if(client .getSensorId().equals(sensorId)){
                        sensorMapper.bindProject(sensorId,projectBindCmd.getProjectId());
                    }
                });
            }
        });
        return Response.buildSuccess();
    }

    @Override
    public Response deleteSensor(Integer id) {
        sensorMapper.deleteById(id);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<SensorBindCO> selectSensorBindArray(Integer positionId) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        List<SensorDO> sensorList = sensorMapper.selectSensorBindByPosition(positionId,tokenData.getGroupId());
        List<SensorBindCO> bindArray = new ArrayList<>(sensorList.size());
        sensorList.forEach(sensorInfo -> {
            SensorBindCO bindCo = new SensorBindCO();
            bindCo.setSensorId(sensorInfo.getId());
            bindCo.setSensorName(sensorInfo.getName());
            bindCo.setSelect(sensorInfo.getProjectId() != null);
            bindArray.add(bindCo);
        });
        return MultiResponse.of(bindArray);
    }
}
