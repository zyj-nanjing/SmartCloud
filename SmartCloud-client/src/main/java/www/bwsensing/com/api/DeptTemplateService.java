package www.bwsensing.com.api;

import com.alibaba.cola.dto.Response;
import www.bwsensing.com.dto.command.DeptTemplateSaveCmd;
import www.bwsensing.com.dto.command.DeptTemplateUpdateCmd;

/**
 * @author macos-zyj
 */
public interface DeptTemplateService {
    /**
     * 新增部门模板
     * @param saveCmd
     * @return
     */
    Response addDepartmentTemplate(DeptTemplateSaveCmd saveCmd);

    /**
     * 修改部门模板
     * @param updateCmd
     * @return
     */
    Response updateDepartmentTemplate(DeptTemplateUpdateCmd updateCmd);

    /**
     * 删除部门模板
     * @param deptId
     * @return
     */
    Response deleteDeptTemplate(Integer deptId);
}
