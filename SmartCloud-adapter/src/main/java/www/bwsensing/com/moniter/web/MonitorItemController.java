package www.bwsensing.com.moniter.web;

import com.alibaba.cola.dto.MultiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.monitor.api.ItemsService;
import www.bwsensing.com.monitor.dto.clientobject.MonitorItemsCO;
import www.bwsensing.com.monitor.dto.command.query.ItemsQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "检测项管理")
@RequestMapping("/api/v1.0/monitor/item")
@RestController
public class MonitorItemController {
    @Autowired
    private ItemsService itemsService;

    @ApiOperation("根据条件查询检测项")
    @PostMapping("/query")
    public MultiResponse<MonitorItemsCO> selectMonitorItemsBySort(@RequestBody ItemsQuery query){
        return itemsService.selectMonitorItemsBySort(query);
    }

    @ApiOperation("根据唯一编码查询检测项")
    @GetMapping("/query/{sn}")
    public MultiResponse<MonitorItemsCO> selectItemsBySn(@PathVariable String sn){
        return itemsService.selectMonitorItemsBySn(sn);
    }

    @ApiOperation("根据ID查询检测项")
    @GetMapping("/query/id/{sessionId}")
    public MultiResponse<MonitorItemsCO> selectItemsBySn(@PathVariable Integer sessionId){
        return itemsService.selectMonitorItemsBySensorId(sessionId);
    }
}
