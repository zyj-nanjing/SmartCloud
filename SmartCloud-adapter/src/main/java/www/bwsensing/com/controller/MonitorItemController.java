package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.IItemsService;
import www.bwsensing.com.dto.clientobject.MonitorItemsCO;
import www.bwsensing.com.dto.command.query.ItemsQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/monitor/item")
@RestController
public class MonitorItemController {
    @Autowired
    private IItemsService itemsService;

    @PostMapping("/query")
    public MultiResponse<MonitorItemsCO> selectMonitorItemsBySort(@RequestBody ItemsQuery query){
        return itemsService.selectMonitorItemsBySort(query);
    }

    @GetMapping("/query/{sn}")
    public MultiResponse<MonitorItemsCO> selectItemsBySn(@PathVariable String sn){
        return itemsService.selectMonitorItemsBySn(sn);
    }
}
