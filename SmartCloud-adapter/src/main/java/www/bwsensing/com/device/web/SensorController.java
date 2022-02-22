package www.bwsensing.com.device.web;

import javax.validation.Valid;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.api.SensorService;
import www.bwsensing.com.device.dto.clientobject.FacilityReceiveCO;
import www.bwsensing.com.device.dto.command.SensorProjectBindCmd;
import www.bwsensing.com.device.dto.command.SensorSaveCmd;
import www.bwsensing.com.device.dto.command.query.FacilityReceivePageQuery;
import www.bwsensing.com.device.dto.command.query.SensorSortQuery;
import www.bwsensing.com.device.dto.command.SensorUpdateCmd;
import www.bwsensing.com.device.dto.clientobject.SensorBindCO;
import www.bwsensing.com.device.dto.clientobject.SensorCO;
import www.bwsensing.com.device.dto.clientobject.SensorMapCO;


/**
 * 传感器信息
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "传感器管理")
@RequestMapping("/api/v1.0/sensor")
@RestController
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @ApiOperation("传感器查询(分页/条件查询)")
    @PostMapping("/query/sort")
    public PageResponse<SensorCO> querySensorBySort(@RequestBody SensorSortQuery sensorSortQuery){
        return sensorService.querySensorBySort(sensorSortQuery);
    }

    @ApiOperation("传感器上报日志查询(分页/条件查询)")
    @PostMapping("/log/query")
    public PageResponse<FacilityReceiveCO> queryFacilitySends(@Valid @RequestBody FacilityReceivePageQuery receivePageQuery){
        return sensorService.queryFacilitySendsBySn(receivePageQuery);
    }

    @ApiOperation("传感器与项目测点绑定")
    @PostMapping("/bind/save")
    public Response bindSensor(@Valid @RequestBody SensorProjectBindCmd projectBindCmd){
        return sensorService.bindSensorInProject(projectBindCmd);
    }

    @ApiOperation("获取项目下可以绑定的传感器(未绑定)")
    @GetMapping("/bind/query/{projectId}")
    public MultiResponse<SensorBindCO> selectSensorBindArray(@PathVariable Integer projectId){
        return sensorService.selectSensorBindArray(projectId);
    }

    @ApiOperation("获取项目下的所有传感器")
    @GetMapping("/project/query/{projectId}")
    public MultiResponse<SensorCO> selectSensorByProjectId(@PathVariable Integer projectId){
        return sensorService.selectSensorByProjectId(projectId);
    }

    @ApiOperation("主页传感器定位接口")
    @GetMapping("/index/map/show")
    public MultiResponse<SensorMapCO> showSensorInMap(){
        return sensorService.showSensorInMap();
    }

    @ApiOperation("创建传感器")
    @PostMapping("/save")
    public Response saveSensor(@Valid @RequestBody SensorSaveCmd saveCmd){
        return sensorService.saveSensor(saveCmd);
    }

    @ApiOperation("修改传感器信息")
    @PostMapping("/update")
    public Response updateSensor(@Valid @RequestBody SensorUpdateCmd updateCmd){
        return sensorService.updateSensor(updateCmd);
    }

    @ApiOperation("根据ID获取传感器详细信息")
    @GetMapping("/query/{id}")
    public SingleResponse<SensorCO> querySensorById(@PathVariable Integer id){
        return sensorService.querySensorById(id);
    }
    @ApiOperation("根据ID删除传感器")
    @GetMapping("/delete/{id}")
    public Response deleteSensor(@PathVariable Integer id){
        return sensorService.deleteSensor(id);
    }
}
