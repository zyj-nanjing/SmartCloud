package www.bwsensing.com.system.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.system.dto.clientobject.DeptTemplateCO;
import www.bwsensing.com.system.dto.command.DeptTemplateSaveCmd;
import www.bwsensing.com.system.dto.command.DeptTemplateUpdateCmd;
import www.bwsensing.com.project.dto.command.query.DeptTemplatePageQuery;

/**
 * @author macos-zyj
 */
public interface DeptTemplateService {
    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    MultiResponse<DeptTemplateCO> pageQueryDeptTemplateBySort(DeptTemplatePageQuery pageQuery);

    /**
     * 根据ID进行查询
     * @param id
     * @return
     */
    SingleResponse<DeptTemplateCO> pathQueryDeptTemplate(Integer id);

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
