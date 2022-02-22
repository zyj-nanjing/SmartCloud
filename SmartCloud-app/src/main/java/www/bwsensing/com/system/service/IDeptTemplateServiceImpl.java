package www.bwsensing.com.system.service;

import javax.annotation.Resource;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.api.DeptTemplateService;
import www.bwsensing.com.system.convertor.DeptTemplateCoConvertor;
import www.bwsensing.com.domain.system.gateway.SysStructureTemplateGateway;
import www.bwsensing.com.domain.system.model.organization.SystemDeptTemplate;
import www.bwsensing.com.system.dto.clientobject.DeptTemplateCO;
import www.bwsensing.com.system.dto.command.DeptTemplateSaveCmd;
import www.bwsensing.com.system.dto.command.DeptTemplateUpdateCmd;
import www.bwsensing.com.project.dto.command.query.DeptTemplatePageQuery;
import www.bwsensing.com.system.gatewayimpl.database.DeptTemplateMapper;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SysDeptTemplateDO;
import java.util.List;


/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class IDeptTemplateServiceImpl implements DeptTemplateService {

    @Resource
    private SysStructureTemplateGateway structureTemplateGateway;
    @Resource
    private DeptTemplateMapper deptTemplateMapper;

    @Override
    public MultiResponse<DeptTemplateCO> pageQueryDeptTemplateBySort(DeptTemplatePageQuery pageQuery) {
        SysDeptTemplateDO dataQuery = new SysDeptTemplateDO();
        BeanUtils.copyProperties(pageQuery, dataQuery);
        List<SysDeptTemplateDO> deptDataCollection = deptTemplateMapper.queryDeptTemplateBySort(dataQuery);
        return MultiResponse.of(DeptTemplateCoConvertor.initDeptTree(deptDataCollection));
    }

    @Override
    public SingleResponse<DeptTemplateCO> pathQueryDeptTemplate(Integer id) {
        SysDeptTemplateDO dataObject = deptTemplateMapper.getDeptTemplateById(id);
        return SingleResponse.of(DeptTemplateCoConvertor.toDeptCo(dataObject));
    }

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
