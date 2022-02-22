package www.bwsensing.com.system.service;

import javax.annotation.Resource;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.api.DepartmentService;
import www.bwsensing.com.system.convertor.DepartmentCoConvertor;
import www.bwsensing.com.system.dto.clientobject.SystemDeptCO;
import www.bwsensing.com.system.dto.command.DepartmentSaveCmd;
import www.bwsensing.com.system.dto.command.DepartmentUpdateCmd;
import www.bwsensing.com.domain.system.gateway.SystemStructureGateway;
import www.bwsensing.com.domain.system.model.organization.SystemDept;
import www.bwsensing.com.project.dto.command.query.DeptPageQuery;
import www.bwsensing.com.system.gatewayimpl.database.SystemDeptMapper;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemDeptDO;

import java.util.List;


/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class IDepartmentServiceImpl implements DepartmentService {
    @Resource
    private SystemStructureGateway structureGateway;

    @Resource
    private SystemDeptMapper deptMapper;

    @Override
    public MultiResponse<SystemDeptCO> pageQueryDepartmentBySort(DeptPageQuery pageQuery) {
        SystemDeptDO queryObject = new SystemDeptDO();
        BeanUtils.copyProperties(pageQuery, queryObject);
        List<SystemDeptDO> dataCollection = deptMapper.selectSystemDeptBySort(queryObject);
        return MultiResponse.of(DepartmentCoConvertor.initDeptTree(dataCollection));
    }

    @Override
    public SingleResponse<SystemDeptCO> getDepartmentById(Integer id) {
        SystemDeptDO dataObject = deptMapper.getSystemDeptById(id);
        return SingleResponse.of(DepartmentCoConvertor.toDeptCo(dataObject));
    }

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
