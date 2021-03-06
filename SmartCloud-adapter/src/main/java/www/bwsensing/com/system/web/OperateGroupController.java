package www.bwsensing.com.system.web;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.system.api.OperateGroupService;
import www.bwsensing.com.system.dto.command.OperateGroupSaveCmd;
import www.bwsensing.com.system.dto.command.OperateGroupUpdateCmd;
import www.bwsensing.com.system.dto.clientobject.OperateGroupCO;
import www.bwsensing.com.common.clientobject.TreeCO;

import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/operate/group")
@RestController
public class OperateGroupController {
    @Autowired
    private OperateGroupService operateGroupService;

    @PostMapping("/save")
    Response saveGroup(@Valid @RequestBody OperateGroupSaveCmd saveGroup){
        return operateGroupService.saveGroup(saveGroup);
    }

    @PostMapping("/update")
    Response saveGroup(@Valid @RequestBody OperateGroupUpdateCmd updateCmd){
        return operateGroupService.updated(updateCmd);
    }

    @GetMapping("/delete/{groupId}")
    Response deleteGroup(@PathVariable Integer groupId){
        return operateGroupService.deleteGroup(groupId);
    }

    @GetMapping("/query")
    MultiResponse<OperateGroupCO>  query(){
        return operateGroupService.showGroupTree();
    }



    @GetMapping("/query/all")
    MultiResponse<OperateGroupCO> queryAllData(){
        return operateGroupService.showGroupTreeByRole();
    }

    @GetMapping("/query/tree")
    MultiResponse<TreeCO> queryGroupTree(){
        return operateGroupService.showGroupTreeCoByRole();
    }
}
