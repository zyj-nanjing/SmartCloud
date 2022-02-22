package www.bwsensing.com.device.web;

import com.alibaba.cola.dto.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.api.SensorDataService;
import www.bwsensing.com.device.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.device.dto.clientobject.SensorDataCO;

/**
 * 传感器数据信息
 * @author macos-zyj
 */
@CrossOrigin
@RequestMapping("/api/v1.0/sensor/data")
@RestController
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping("/query/sort")
    public PageResponse<SensorDataCO>  sensorDataQuerySort(@RequestBody SensorDataSortQuery sensorDataSortQuery){
        return sensorDataService.sensorDataQuerySort(sensorDataSortQuery);
    }
}
