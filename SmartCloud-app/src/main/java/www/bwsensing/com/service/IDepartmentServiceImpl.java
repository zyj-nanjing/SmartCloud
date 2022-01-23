package www.bwsensing.com.service;

import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.DepartmentService;
import www.bwsensing.com.dto.command.DepartmentSaveCmd;
import www.bwsensing.com.dto.command.DepartmentUpdateCmd;
import www.bwsensing.com.domain.gateway.SystemStructureGateway;
import www.bwsensing.com.domain.system.organization.SystemDept;


/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class IDepartmentServiceImpl implements DepartmentService {
    @Resource
    private SystemStructureGateway structureGateway;

    @Override
    public Response addDepartment(DepartmentSaveCmd saveCmd) {
        SystemDept domainDept = new SystemDept();
        BeanUtils.copyProperties(saveCmd, domainDept);
        structureGateway.addDepartment(domainDept);
        return Response.buildSuccess();
    }

    @Override
    public Response updateDepartment(DepartmentUpdateCmd updateCmd) {
        SystemDept domainDept = new SystemDept();
        BeanUtils.copyProperties(updateCmd, domainDept);
        structureGateway.updateDepartment(domainDept);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteDept(Integer deptId) {
        return null;
    }
}
