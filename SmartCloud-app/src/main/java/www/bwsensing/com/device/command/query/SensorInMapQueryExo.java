package www.bwsensing.com.device.command.query;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.MultiResponse;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.project.gateway.ProjectMonitorGateway;
import www.bwsensing.com.domain.project.model.MonitorProject;
import www.bwsensing.com.device.dto.clientobject.SensorMapCO;
import www.bwsensing.com.device.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorDO;


/**
 * @author macos-zyj
 */
@Component
public class SensorInMapQueryExo {
    @Resource
    private ProjectMonitorGateway projectMonitorGateway;
    @Resource
    private SensorMapper sensorMapper;

    public MultiResponse<SensorMapCO> execute(){
        List<SensorMapCO> sensorMapCollection = new ArrayList<>();
        List<MonitorProject>  monitorProjects = projectMonitorGateway.selectProjectByPermission();
        monitorProjects.forEach(project ->{
            List<SensorDO> sensorArray = sensorMapper.selectSensorByProjectId(project.getId());
            sensorArray.forEach(dataObject -> sensorMapCollection.add(initSensorInMapCo(project,dataObject)));
        });
        return MultiResponse.of(sensorMapCollection);
    }

    private  SensorMapCO initSensorInMapCo(MonitorProject project,SensorDO dataObject){
        SensorMapCO mapDataObject = new SensorMapCO();
        mapDataObject.setProjectId(project.getId());
        mapDataObject.setProjectName(project.getName());
        mapDataObject.setSensorName(dataObject.getName());
        mapDataObject.setSensorModel(dataObject.getModelName());
        mapDataObject.setLongitude(dataObject.getLongitude());
        mapDataObject.setLatitude(dataObject.getLatitude());
        mapDataObject.setTemperature(dataObject.getTemperature());
        mapDataObject.setElectricity(dataObject.getElectricity());
        return mapDataObject;
    }
}
