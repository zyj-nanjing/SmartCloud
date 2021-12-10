package www.bwsensing.com.command.query;

import com.alibaba.cola.dto.MultiResponse;
import org.springframework.stereotype.Component;
import www.bwsensing.com.dto.clientobject.ProjectPositionCO;
import www.bwsensing.com.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPositionDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorProjectDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorStructureDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class ProjectPositionQueryExo {
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private SensorMapper sensorMapper;

    public MultiResponse<ProjectPositionCO> execute(Integer projectId){
        List<ProjectPositionCO> projectPositions = new ArrayList<>(16);
        MonitorProjectDO projectDataObject = projectMapper.selectMonitorProjectById(projectId);
        projectDataObject.getStructureArray().forEach( structureDataObject ->{
            projectPositions.addAll(toProjectsPosition(structureDataObject));
        });
        return MultiResponse.of(projectPositions);
    }


    private List<ProjectPositionCO> toProjectsPosition(MonitorStructureDO structureDataObject){
        List<MonitorPositionDO> positionArray = structureDataObject.getPositionList();
        List<ProjectPositionCO> projectPositions = new ArrayList<>(16);
        positionArray.forEach( position ->{
            projectPositions.add(initPositionData(position,structureDataObject));
        });
        return projectPositions;
    }

    private ProjectPositionCO initPositionData(MonitorPositionDO positionData,MonitorStructureDO structureData){
        ProjectPositionCO clientObject = new ProjectPositionCO();
        clientObject.setId(positionData.getId());
        clientObject.setPositionName(positionData.getName());
        clientObject.setStructureName(structureData.getStructureName());
        clientObject.setStructureVersion(structureData.getCurrentVersion());
        SensorDO querySensor = sensorMapper.selectSensorByPosition(positionData.getId());
        if(null != querySensor){
            clientObject.setSensorId(querySensor.getId());
            clientObject.setBindStatus(true);
        } else{
            clientObject.setBindStatus(false);
        }
        return clientObject;
    }

}
