package www.bwsensing.com.system.web;

import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.system.api.SystemClientService;
import www.bwsensing.com.system.dto.clientobject.SystemClientCO;
import www.bwsensing.com.system.dto.command.SystemClientSaveCmd;
import www.bwsensing.com.system.dto.command.SystemClientUpdateCmd;
import org.springframework.validation.annotation.Validated;
import www.bwsensing.com.project.dto.command.query.SystemClientSortQuery;


/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "客户管理")
@RequestMapping("/api/v1.0/system/client")
@RestController
public class SystemClientController {
    @Resource
    private SystemClientService systemClientService;

    @ApiOperation("新增客户")
    @PostMapping("/save")
    public Response addSystemClient(@Valid @RequestBody SystemClientSaveCmd saveCmd){
        return systemClientService.addSystemClient(saveCmd);
    }

    @ApiOperation("修改客户")
    @PostMapping("/update")
    public Response updateSystemClient(@Valid @RequestBody SystemClientUpdateCmd updateCmd){
        return systemClientService.updateSystemClient(updateCmd);
    }

    @ApiOperation("查询客户列表(用于SELECT)")
    @PostMapping("/query")
    public MultiResponse<SystemClientCO> querySystemClientBySort(@RequestBody SystemClientSortQuery sortQuery){
        return systemClientService.queryClientBySort(sortQuery);
    }


    @ApiOperation("分页查询客户列表")
    @PostMapping("/page/query")
    public PageResponse<SystemClientCO> querySystemClient(@RequestBody SystemClientSortQuery pageQuery){
        return systemClientService.queryClientPageBySort(pageQuery);
    }

    @ApiOperation("根据ID查询客户详情")
    @GetMapping("/query/{id}")
    public SingleResponse<SystemClientCO> pathQuerySystemClient(@PathVariable Integer id){
        return systemClientService.getSystemClientInfo(id);
    }
}
