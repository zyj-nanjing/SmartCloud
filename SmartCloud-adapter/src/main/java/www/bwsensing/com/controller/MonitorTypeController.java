package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.IMonitorService;
import www.bwsensing.com.dto.clientobject.PrototypeCO;
import www.bwsensing.com.dto.command.PrototypeAddCmd;
import www.bwsensing.com.dto.command.PrototypeUpdateCmd;
import www.bwsensing.com.dto.command.query.PrototypeSortQuery;

import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/monitor")
@RestController
public class MonitorTypeController {

    @Autowired
    private IMonitorService monitorService;

    @PostMapping("/prototype/save")
    public Response savePrototype(@Valid @RequestBody PrototypeAddCmd prototypeAddCmd){
        return monitorService.save(prototypeAddCmd);
    }

    @PostMapping("/prototype/update")
    public Response updatePrototype(@Valid @RequestBody PrototypeUpdateCmd updateCmd){
        return monitorService.update(updateCmd);
    }
    @GetMapping("/prototype/delete/{typeId}")
    public Response deletePrototype(@PathVariable Integer typeId){
        return monitorService.delete(typeId);
    }
    @GetMapping("/type/query")
    public MultiResponse<PrototypeCO> showAllPrototype(){
        return monitorService.showAllPrototype();
    }

    @PostMapping("/type/sort")
    public PageResponse<PrototypeCO> selectPrototypeBySort(@RequestBody PrototypeSortQuery typeSortQuery)
    {
        return monitorService.selectPrototypeBySort(typeSortQuery);
    }

    @GetMapping("/prototype/types/{typeId}")
    public SingleResponse<PrototypeCO> selectByTypeId(@PathVariable Integer typeId){
        return monitorService.selectPrototypeById(typeId);
    }
}
