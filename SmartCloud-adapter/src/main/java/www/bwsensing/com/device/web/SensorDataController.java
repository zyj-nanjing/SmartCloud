package www.bwsensing.com.device.web;


import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.api.SensorDataService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.dto.clientobject.SensorDataCO;
import www.bwsensing.com.device.dto.command.query.SensorTableQuery;
import www.bwsensing.com.device.dto.clientobject.SensorDynamicTableCO;
import www.bwsensing.com.device.dto.command.query.SensorDataSortQuery;


/**
 * 传感器数据信息
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "传感器数据")
@RequestMapping("/api/v1.0/sensor/data")
@RestController
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping("/query/sort")
    public PageResponse<SensorDataCO>  sensorDataQuerySort(@RequestBody SensorDataSortQuery sensorDataSortQuery){
        return sensorDataService.sensorDataQuerySort(sensorDataSortQuery);
    }


    @ApiOperation("动态表格查询")
    @PostMapping("/dynamic/table/query")
    public SingleResponse<SensorDynamicTableCO> sensorDynamicTableQuery(@Valid @RequestBody SensorTableQuery tableQuery){
        return sensorDataService.sensorDynamicTableQuery(tableQuery);
    }


}
