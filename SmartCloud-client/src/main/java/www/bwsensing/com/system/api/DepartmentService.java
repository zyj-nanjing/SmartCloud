package www.bwsensing.com.system.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.system.dto.clientobject.SystemDeptCO;
import www.bwsensing.com.system.dto.command.DepartmentSaveCmd;
import www.bwsensing.com.system.dto.command.DepartmentUpdateCmd;
import www.bwsensing.com.project.dto.command.query.DeptPageQuery;


/**
 * 部门服务
 * @author macos-zyj
 */
public interface DepartmentService {

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    MultiResponse<SystemDeptCO> pageQueryDepartmentBySort(DeptPageQuery pageQuery);


    /**
     * 根据编号获取部门
     * @param id
     * @return
     */
    SingleResponse<SystemDeptCO> getDepartmentById(Integer id);
    /**
     * 新增部门模板
     * @param saveCmd
     * @return
     */
    Response addDepartment(DepartmentSaveCmd saveCmd);

    /**
     * 修改部门模板
     * @param updateCmd
     * @return
     */
    Response updateDepartment(DepartmentUpdateCmd updateCmd);

    /**
     * 删除部门模板
     * @param deptId
     * @return
     */
    Response deleteDept(Integer deptId);
}
