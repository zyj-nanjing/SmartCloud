package www.bwsensing.com.controller;

import javax.validation.Valid;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.TemplateService;
import www.bwsensing.com.dto.command.AlarmTemplateSaveCmd;
import org.springframework.validation.annotation.Validated;
import www.bwsensing.com.dto.command.AlarmTemplateUpdateCmd;
import www.bwsensing.com.dto.clientobject.AlertTemplateCO;
import www.bwsensing.com.dto.command.query.AlertTemplateQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "告警模板管理")
@RequestMapping("/api/v1.0/alert/template")
@RestController
public class AlertTemplateController {

    @Autowired
    private TemplateService templateService;

    @ApiOperation("保存告警模板")
    @PostMapping("/save")
    public Response saveTemplate(@Valid @RequestBody AlarmTemplateSaveCmd saveCmd){
        return templateService.saveTemplate(saveCmd);
    }

    @ApiOperation("修改告警模板")
    @PostMapping("/update")
    public Response updateTemplate(@Valid @RequestBody AlarmTemplateUpdateCmd update){
        return templateService.updateTemplate(update);
    }

    @ApiOperation("告警模板查询(分页)")
    @PostMapping("/page/query")
    public PageResponse<AlertTemplateCO> selectAlertTemplate(@RequestBody AlertTemplateQuery pageQuery){
        return templateService.selectTemplateBySort(pageQuery);
    }


    @ApiOperation("告警模板查询(根据编号查询)")
    @GetMapping("/query/{templateId}")
    public SingleResponse<AlertTemplateCO> selectAlertTemplateById(@PathVariable Integer templateId){
        return templateService.selectAlertTemplateById(templateId);
    }
}
