package www.bwsensing.com.device.web;


import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import com.alibaba.cola.dto.MultiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.api.DataComputationModelService;
import www.bwsensing.com.device.dto.clientobject.DataComputationModelCO;
import www.bwsensing.com.device.dto.command.DataComputationModelSaveCmd;
import www.bwsensing.com.device.dto.command.DataComputationModelUpdateCmd;
import www.bwsensing.com.device.dto.command.query.DataComputationModelPageQuery;


/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "计算模型")
@RequestMapping("/api/v1.0/data/computation")
@RestController
public class DataComputationModelController {

    @Autowired
    private DataComputationModelService dataComputationModelService;


    @ApiOperation("新增计算模型")
    @PostMapping("/add")
    public Response addDataComputationModel(@Valid @RequestBody DataComputationModelSaveCmd saveCmd){
        return dataComputationModelService.addDataComputationModel(saveCmd);
    }

    @ApiOperation("修改计算模型")
    @PostMapping("/update")
    public Response updateDataComputationModel(@Valid @RequestBody DataComputationModelUpdateCmd updateCmd){
        return dataComputationModelService.updateDataComputationModel(updateCmd);
    }

    @ApiOperation("删除计算模型")
    @PostMapping("/delete/{id}")
    public Response deleteDataComputationModelById(@PathVariable Integer id){
        return dataComputationModelService.deleteDataComputationModel(id);
    }

    @ApiOperation("查看计算模型配置详情")
    @GetMapping("/detail/{id}")
    public SingleResponse<DataComputationModelCO> getDataComputationModelById(@PathVariable Integer id){
        return dataComputationModelService.getDataComputationModelById(id);
    }


    @ApiOperation("根据产品编号获取计算模型列表")
    @GetMapping("/query/{modelId}")
    public MultiResponse<DataComputationModelCO> getDataComputationModelByModelId(@PathVariable Integer modelId){
        return dataComputationModelService.getDataComputationModelByModelId(modelId);
    }


    @ApiOperation("计算模型配置分页查询")
    @PostMapping("/page/query")
    public PageResponse<DataComputationModelCO> getDataComputationModelBySort(@Valid @RequestBody DataComputationModelPageQuery pageQuery){
        return dataComputationModelService.getDataComputationModelBySort(pageQuery);
    }


}
