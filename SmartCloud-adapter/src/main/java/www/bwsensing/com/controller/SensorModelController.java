package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import javax.validation.Valid;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import www.bwsensing.com.api.ISensorModelService;
import www.bwsensing.com.dto.SensorModelVO;
import www.bwsensing.com.dto.clientobject.ImportResultCO;
import www.bwsensing.com.dto.command.SensorModelSaveCmd;
import www.bwsensing.com.dto.command.SensorModelUpdateCmd;
import www.bwsensing.com.dto.clientobject.SensorModelCO;
import www.bwsensing.com.listener.SensorModelDataListener;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/sensor/model")
@RestController
public class SensorModelController {
    @Autowired
    private ISensorModelService sensorModelService;

    @PostMapping("/save")
    public Response saveModel(@Valid @RequestBody SensorModelSaveCmd saveCmd){
        return  sensorModelService.saveModel( saveCmd);
    }

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
    @RequestMapping("/importData")
    public SingleResponse<ImportResultCO>  importData(@RequestParam MultipartFile file) throws Exception
    {
        SensorModelDataListener dataListener = new SensorModelDataListener(sensorModelService);
        EasyExcel.read(file.getInputStream(), SensorModelVO.class, dataListener).sheet().doRead();
        return SingleResponse.of(dataListener.getImportResult());
    }

    @GetMapping("/delete/{modelId}")
    public Response deleteModel(@PathVariable Integer modelId){
        return sensorModelService.deleteModel( modelId);
    }

    @GetMapping("/query")
    public MultiResponse<SensorModelCO> queryAllModels(){
        return sensorModelService.queryAllModels();
    }

    @GetMapping("/query/{modelId}")
    public SingleResponse<SensorModelCO> selectModelById(@PathVariable Integer modelId){
        return sensorModelService.selectModelById(modelId);
    }
}
