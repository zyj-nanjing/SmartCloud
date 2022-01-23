package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import www.bwsensing.com.api.StructureModelService;
import www.bwsensing.com.dto.export.PositionModelVo;
import www.bwsensing.com.dto.export.StructureModelVo;
import www.bwsensing.com.dto.command.query.BaseQuery;
import www.bwsensing.com.dto.command.StructureModelSaveCmd;
import www.bwsensing.com.dto.command.StructureModelUpdateCmd;
import www.bwsensing.com.dto.clientobject.StructureModelCO;
import www.bwsensing.com.listener.PositionModelListener;
import www.bwsensing.com.listener.StructureModelDataListener;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "结构物模板管理")
@RequestMapping("/api/v1.0/monitor/structure")
@RestController
public class MonitorStructureController {
    @Autowired
    private StructureModelService structureModelService;

    @ApiOperation("新增结构物模板")
    @PostMapping("/model/save")
    public Response save(@Valid @RequestBody StructureModelSaveCmd structureSaveCmd){
        return structureModelService.save(structureSaveCmd);
    }

    @ApiOperation("修改结构物模板")
    @PostMapping("/model/update")
    public Response update(@Valid @RequestBody StructureModelUpdateCmd updateCmd){
        return structureModelService.update(updateCmd);
    }
    @ApiOperation("根据结构物模板编码进行删除")
    @GetMapping("/model/delete/{code}")
    public Response delete(@PathVariable String code){
        return structureModelService.deleteStructure(code);
    }

    /**
     * Excel Test
     * @param file 文件流
     * @return
     * @throws Exception
     */
    @ApiOperation("结构物模板Excel导入")
    @PostMapping("/model/importData")
    public SingleResponse importData(@RequestParam MultipartFile file){
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(file.getInputStream()).build();
            StructureModelDataListener sensorModelDataListener = new StructureModelDataListener();
            PositionModelListener positionModelListener = new PositionModelListener();
            // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(StructureModelVo.class).registerReadListener(sensorModelDataListener).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(PositionModelVo.class).registerReadListener(positionModelListener).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2);
            return structureModelService.importStructure(sensorModelDataListener.getImportCollection(),positionModelListener.getImportCollection());
        } catch (IOException e) {
            return  SingleResponse.buildFailure("FILE_IO_EXCEPTION","文件读取有误");
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    @ApiOperation("结构物模板查询(非分页)")
    @GetMapping("/model/query")
    public MultiResponse<StructureModelCO> selectStructureShow(){
        return structureModelService.selectStructureShow();
    }

    @ApiOperation("结构物模板查询(分页)")
    @PostMapping("/model/page/query")
    public PageResponse<StructureModelCO> selectStructurePage(@RequestBody BaseQuery baseQuery){
        return structureModelService.selectStructurePages(baseQuery);
    }

    @ApiOperation("结构物模板查询(根据结构物编码)")
    @GetMapping("/model/query/{code}")
    public SingleResponse<StructureModelCO> selectStructureByCode(@PathVariable String code){
        return structureModelService.selectStructureByCode(code);
    }
    @ApiOperation("结构物模板查询(根据ID)")
    @GetMapping("/model/history/query/{structureId}")
    public SingleResponse<StructureModelCO> selectStructureById(@PathVariable Integer structureId){
        return structureModelService.selectStructureById(structureId);
    }

}
