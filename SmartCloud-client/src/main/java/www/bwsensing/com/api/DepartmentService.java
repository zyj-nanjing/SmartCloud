package www.bwsensing.com.api;

import com.alibaba.cola.dto.Response;
import www.bwsensing.com.dto.command.DepartmentSaveCmd;
import www.bwsensing.com.dto.command.DepartmentUpdateCmd;

/**
 * 部门服务
 * @author macos-zyj
 */
public interface DepartmentService {
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
