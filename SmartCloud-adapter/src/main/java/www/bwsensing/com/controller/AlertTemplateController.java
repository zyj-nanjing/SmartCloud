package www.bwsensing.com.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.ITemplateService;
import www.bwsensing.com.dto.clientobject.AlertTemplateCO;
import www.bwsensing.com.dto.command.AlarmTemplateSaveCmd;
import www.bwsensing.com.dto.command.AlarmTemplateUpdateCmd;
import www.bwsensing.com.dto.command.query.AlertTemplateQuery;

import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/alert/template")
@RestController
public class AlertTemplateController {

    @Autowired
    private ITemplateService templateService;

    @PostMapping("/save")
    public Response saveTemplate(@Valid @RequestBody AlarmTemplateSaveCmd saveCmd){
        return templateService.saveTemplate(saveCmd);
    }


    @PostMapping("/update")
    public Response updateTemplate(@Valid @RequestBody AlarmTemplateUpdateCmd update){
        return templateService.updateTemplate(update);
    }


    @PostMapping("/page/query")
    public PageResponse<AlertTemplateCO> selectAlertTemplate(@RequestBody AlertTemplateQuery pageQuery){
        return templateService.selectTemplateBySort(pageQuery);
    }



    @GetMapping("/query/{templateId}")
    public SingleResponse<AlertTemplateCO> selectAlertTemplateById(@PathVariable Integer templateId){
        return templateService.selectAlertTemplateById(templateId);
    }
}
