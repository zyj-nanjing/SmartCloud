package www.bwsensing.com.service;

import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.DeptTemplateService;
import www.bwsensing.com.domain.gateway.SysStructureTemplateGateway;
import www.bwsensing.com.domain.system.organization.SystemDeptTemplate;
import www.bwsensing.com.dto.command.DeptTemplateSaveCmd;
import www.bwsensing.com.dto.command.DeptTemplateUpdateCmd;


/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class IDeptTemplateServiceImpl implements DeptTemplateService {

    @Resource
    private SysStructureTemplateGateway structureTemplateGateway;


    @Override
    public Response addDepartmentTemplate(DeptTemplateSaveCmd saveCmd) {
        SystemDeptTemplate domainDept = new SystemDeptTemplate();
        BeanUtils.copyProperties(saveCmd, domainDept);
        structureTemplateGateway.addDepartment(domainDept);
        return Response.buildSuccess();
    }

    @Override
    public Response updateDepartmentTemplate(DeptTemplateUpdateCmd updateCmd) {
        SystemDeptTemplate domainDept = new SystemDeptTemplate();
        BeanUtils.copyProperties(updateCmd, domainDept);
        structureTemplateGateway.updateDepartment(domainDept);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteDeptTemplate(Integer deptId) {
        return Response.buildSuccess();
    }
}
