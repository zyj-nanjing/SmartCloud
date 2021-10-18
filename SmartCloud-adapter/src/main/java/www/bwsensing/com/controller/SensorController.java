package www.bwsensing.com.controller;

import javax.validation.Valid;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.ISensorService;
import www.bwsensing.com.dto.command.SensorProjectBindCmd;
import www.bwsensing.com.dto.command.SensorSaveCmd;
import www.bwsensing.com.dto.command.query.SensorSortQuery;
import www.bwsensing.com.dto.command.SensorUpdateCmd;
import www.bwsensing.com.dto.clientobject.SensorBindCO;
import www.bwsensing.com.dto.clientobject.SensorCO;
import www.bwsensing.com.dto.clientobject.SensorMapCO;


/**
 * 传感器信息
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/sensor")
@RestController
public class SensorController {
    @Autowired
    private ISensorService sensorService;

    @PostMapping("/query/sort")
    public PageResponse<SensorCO> querySensorBySort(@RequestBody SensorSortQuery sensorSortQuery){
        return sensorService.querySensorBySort(sensorSortQuery);
    }

    @PostMapping("/bind/save")
    public Response bindSensor(@Valid @RequestBody SensorProjectBindCmd projectBindCmd){
        return sensorService.bindSensorInProject(projectBindCmd);
    }

    @GetMapping("/bind/query/{positionId}")
    public MultiResponse<SensorBindCO> selectSensorBindArray(@PathVariable Integer positionId){
        return sensorService.selectSensorBindArray(positionId);
    }

    @GetMapping("/project/query/{projectId}")
    public MultiResponse<SensorCO> selectSensorByProjectId(@PathVariable Integer projectId){
        return sensorService.selectSensorByProjectId(projectId);
    }

    @GetMapping("/index/map/show")
    public MultiResponse<SensorMapCO> showSensorInMap(){
        return sensorService.showSensorInMap();
    }


    @PostMapping("/save")
    public Response saveSensor(@Valid @RequestBody SensorSaveCmd saveCmd){
        return sensorService.saveSensor(saveCmd);
    }

    @PostMapping("/update")
    public Response updateSensor(@Valid @RequestBody SensorUpdateCmd updateCmd){
        return sensorService.updateSensor(updateCmd);
    }

    @GetMapping("/query/{id}")
    public SingleResponse<SensorCO> querySensorById(@PathVariable Integer id){
        return sensorService.querySensorById(id);
    }

    @GetMapping("/delete/{id}")
    public Response deleteSensor(@PathVariable Integer id){
        return sensorService.deleteSensor(id);
    }
}
