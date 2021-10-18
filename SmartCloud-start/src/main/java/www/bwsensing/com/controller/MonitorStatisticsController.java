package www.bwsensing.com.controller;

import java.util.Map;

import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.project.visualization.domain.MonitorQuery;
import www.bwsensing.com.project.visualization.domain.StatisticsResult;
import www.bwsensing.com.project.visualization.service.IMonitorStatisticsService;


/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/monitor")
@RestController
public class MonitorStatisticsController {
    @Autowired
    private IMonitorStatisticsService monitorService;


    @PostMapping("/device/data/analyse")
    public SingleResponse<Map<String, StatisticsResult>> singleDataAnalyse(@RequestBody MonitorQuery query){
        return monitorService.singleDataAnalyse(query);
    }
}
