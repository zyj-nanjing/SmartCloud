package www.bwsensing.com.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import www.bwsensing.com.api.IProjectMemberService;
import www.bwsensing.com.api.ISensorService;
import www.bwsensing.com.command.SensorSaveCmdExo;
import www.bwsensing.com.command.SensorUpdateCmdExo;
import www.bwsensing.com.command.query.SensorInMapQueryExo;
import www.bwsensing.com.command.query.SensorSortQueryExo;
import www.bwsensing.com.convertor.FacilityReceiveCoConvertor;
import www.bwsensing.com.convertor.SensorCoConvertor;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.project.ProjectRoleEnum;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.clientobject.FacilityReceiveCO;
import www.bwsensing.com.dto.clientobject.SensorBindCO;
import www.bwsensing.com.dto.clientobject.SensorCO;
import www.bwsensing.com.dto.clientobject.SensorMapCO;
import www.bwsensing.com.dto.command.SensorProjectBindCmd;
import www.bwsensing.com.dto.command.SensorSaveCmd;
import www.bwsensing.com.dto.command.SensorUpdateCmd;
import www.bwsensing.com.dto.command.query.FacilityReceivePageQuery;
import www.bwsensing.com.dto.command.query.SensorSortQuery;
import www.bwsensing.com.gatewayimpl.database.MonitorReceiveMapper;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorReceiveDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class SensorServiceImpl implements ISensorService {
    @Resource
    private SensorSortQueryExo sortQueryExo;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private IProjectMemberService projectMemberService;
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


    @Override
    public PageResponse<SensorCO> querySensorBySort(SensorSortQuery sortQuery) {
        return sortQueryExo.execute(sortQuery);
    }

    @Override
    public PageResponse<FacilityReceiveCO> queryFacilitySendsBySn(FacilityReceivePageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        MonitorReceiveDO querySortQuery = new MonitorReceiveDO();
        querySortQuery.setSn(pageQuery.getSn());
        List<MonitorReceiveDO> receiveCollection = monitorReceiveMapper.selectMonitorReceiveBySort(querySortQuery);
        PageInfo<MonitorReceiveDO> pageInfo = new PageInfo<>(receiveCollection);
        List<FacilityReceiveCO> result = FacilityReceiveCoConvertor.toClientObjectList(receiveCollection);
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
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
