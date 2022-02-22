package www.bwsensing.com.moniter.web;

import com.alibaba.cola.dto.MultiResponse;
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
@RequestMapping("/api/v1.0/monitor/item")
@RestController
public class MonitorItemController {
    @Autowired
    private ItemsService itemsService;

    @PostMapping("/query")
    public MultiResponse<MonitorItemsCO> selectMonitorItemsBySort(@RequestBody ItemsQuery query){
        return itemsService.selectMonitorItemsBySort(query);
    }

    @GetMapping("/query/{sn}")
    public MultiResponse<MonitorItemsCO> selectItemsBySn(@PathVariable String sn){
        return itemsService.selectMonitorItemsBySn(sn);
    }
}
