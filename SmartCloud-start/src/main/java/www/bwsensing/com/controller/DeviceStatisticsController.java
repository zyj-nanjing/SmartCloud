package www.bwsensing.com.controller;

import java.util.Map;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.project.visualization.domain.DeviceDataQuery;
import www.bwsensing.com.project.visualization.domain.StatisticsResult;
import www.bwsensing.com.project.visualization.service.DeviceStatisticsService;


/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "数据可视化")
@RequestMapping("/api/v1.0/monitor")
@RestController
public class DeviceStatisticsController {
    @Autowired
    private DeviceStatisticsService monitorService;

    @ApiOperation("单设备多参数可视化分析")
    @PostMapping("/device/data/analyse")
    public SingleResponse<Map<String, StatisticsResult>> singleDataAnalyse(@RequestBody DeviceDataQuery query){
        return monitorService.singleDataAnalyse(query);
    }
}
