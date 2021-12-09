package www.bwsensing.com.controller;

import com.alibaba.cola.dto.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.ISensorDataService;
import www.bwsensing.com.dto.clientobject.SensorDataCO;
import www.bwsensing.com.dto.command.query.SensorDataSortQuery;

/**
 * 传感器数据信息
 * @author macos-zyj
 */
@CrossOrigin
@RequestMapping("/api/v1.0/sensor/data")
@RestController
public class SensorDataController {
    @Autowired
    private ISensorDataService sensorDataService;

    @PostMapping("/query/sort")
    public PageResponse<SensorDataCO>  sensorDataQuerySort(@RequestBody SensorDataSortQuery sensorDataSortQuery){
        return sensorDataService.sensorDataQuerySort(sensorDataSortQuery);
    }
}
