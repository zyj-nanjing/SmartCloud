package www.bwsensing.com.controller;

import javax.validation.Valid;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.IManufacturerService;
import www.bwsensing.com.dto.command.ManufacturerSaveCmd;
import www.bwsensing.com.dto.command.ManufacturerUpdateCmd;
import www.bwsensing.com.dto.clientobject.ManufacturerCO;
import www.bwsensing.com.dto.command.query.ManufacturerSortQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/manufacturer")
@RestController
public class ManufacturerController {
    @Autowired
    private IManufacturerService manufacturerService;

    /**
     * 保存
     * @param saveCmd
     * @return
     */
    @PostMapping("/save")
    public Response saveManufacturer(@Valid  @RequestBody ManufacturerSaveCmd saveCmd){
        return manufacturerService.saveManufacturer(saveCmd);
    }

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    @PostMapping("/update")
    public Response updateManufacturer(@Valid  @RequestBody  ManufacturerUpdateCmd updateCmd){
        return manufacturerService.updateManufacturer(updateCmd);
    }

    /**
     * 查询根据编号获取厂商
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public SingleResponse<ManufacturerCO> selectManufacturerById(@PathVariable Integer id){
        return manufacturerService.selectManufacturerById(id);
    }

    /**
     * 下拉框选择
     * @return
     */
    @GetMapping("/query")
    public MultiResponse<ManufacturerCO> selectManufactureShow(){
        return manufacturerService.selectManufactureShow();
    }
    /**
     * 条件查询
     * @param pageQuery
     * @return
     */
    @PostMapping("/query/page")
    public PageResponse<ManufacturerCO> selectManufactureBySort(@RequestBody  ManufacturerSortQuery pageQuery){
        return manufacturerService.selectManufactureBySort(pageQuery);
    }
}
