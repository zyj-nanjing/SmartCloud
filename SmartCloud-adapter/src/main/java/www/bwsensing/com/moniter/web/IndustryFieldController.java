package www.bwsensing.com.moniter.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.monitor.api.IndustryFieldService;
import www.bwsensing.com.monitor.dto.command.IndustryFieldSaveCmd;
import www.bwsensing.com.monitor.dto.command.IndustryFieldUpdateCmd;
import www.bwsensing.com.monitor.dto.clientobject.IndustryFieldCO;
import www.bwsensing.com.monitor.dto.command.query.IndustryFileSortQuery;

import javax.validation.Valid;

/**
 * 行业领域
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "行业领域管理")
@RequestMapping("/api/v1.0/industry/filed")
@RestController
public class IndustryFieldController {

    @Autowired
    private IndustryFieldService industryFieldService;

    /**
     * 保存行业领域
     * @param saveCmd
     * @return
     */
    @ApiOperation("新增行业领域")
    @PostMapping("/save")
    public Response saveIndustryField(@Valid @RequestBody IndustryFieldSaveCmd saveCmd){
        return industryFieldService.saveIndustryField(saveCmd);
    }


    /**
     * 修改行业领域
     * @param updateCmd
     * @return
     */
    @ApiOperation("修改行业领域")
    @PostMapping("/update")
    public Response updateIndustryField(@Valid @RequestBody IndustryFieldUpdateCmd updateCmd){
        return industryFieldService.updateIndustryField(updateCmd);
    }

    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    @ApiOperation("新增行业领域")
    @GetMapping("/query/{id}")
    public SingleResponse<IndustryFieldCO> getIndustryFieldInfo(@PathVariable Integer id){
        return industryFieldService.selectIndustryFieldById(id);
    }

    /**
     * 查询所有
     * @return
     */
    @ApiOperation("查询行业领域(SELECT)")
    @PostMapping("/query")
    public MultiResponse<IndustryFieldCO> selectIndustryFileQuery(@RequestBody IndustryFileSortQuery sortQuery){
        return industryFieldService.selectIndustryFileQuery(sortQuery);
    }

    /**
     * 表格查询
     * @param sortQuery
     * @return
     */
    @ApiOperation("查询行业领域分页")
    @PostMapping("/page/query")
    public PageResponse<IndustryFieldCO> selectIndustryFileBySortPage(@RequestBody IndustryFileSortQuery sortQuery){
        return industryFieldService.selectIndustryFileBySortPage(sortQuery);
    }
}
