package www.bwsensing.com.device.web;

import javax.validation.Valid;
import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.api.SensorModelService;
import www.bwsensing.com.device.export.SensorModelVO;
import org.springframework.web.multipart.MultipartFile;
import www.bwsensing.com.device.dto.command.SensorModelSaveCmd;
import www.bwsensing.com.device.dto.clientobject.SensorModelCO;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import www.bwsensing.com.device.dto.command.SensorModelUpdateCmd;
import org.springframework.validation.annotation.Validated;
import www.bwsensing.com.device.listener.SensorModelDataListener;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "产品型号管理")
@RequestMapping("/api/v1.0/sensor/model")
@RestController
public class SensorModelController {
    @Autowired
    private SensorModelService sensorModelService;

    @ApiOperation("新增产品型号")
    @PostMapping("/save")
    public Response saveModel(@Valid @RequestBody SensorModelSaveCmd saveCmd){
        return  sensorModelService.saveModel( saveCmd);
    }

    @ApiOperation("修改产品型号")
    @PostMapping("/update")
    public Response updateModel(@Valid @RequestBody SensorModelUpdateCmd updateCmd){
        return  sensorModelService.updateModel(updateCmd);
    }

    /**
     * Excel Test
     * @param file 文件流
     * @return
     * @throws Exception
     */
    @ApiOperation("导入产品型号")
    @PostMapping("/importData")
    public SingleResponse<ImportResultCO>  importData(@RequestParam MultipartFile file) throws Exception
    {
        SensorModelDataListener dataListener = new SensorModelDataListener(sensorModelService);
        EasyExcel.read(file.getInputStream(), SensorModelVO.class, dataListener).sheet().doRead();
        return SingleResponse.of(dataListener.getImportResult());
    }

    @ApiOperation("根据ID删除产品型号")
    @GetMapping("/delete/{modelId}")
    public Response deleteModel(@PathVariable Integer modelId){
        return sensorModelService.deleteModel( modelId);
    }

    @ApiOperation("产品型号查询(非分页)")
    @GetMapping("/query")
    public MultiResponse<SensorModelCO> queryAllModels(){
        return sensorModelService.queryAllModels();
    }

    @ApiOperation("根据编号查询产品型号详细信息")
    @GetMapping("/query/{modelId}")
    public SingleResponse<SensorModelCO> selectModelById(@PathVariable Integer modelId){
        return sensorModelService.selectModelById(modelId);
    }
}
