package www.bwsensing.com.domain.system.gateway;

import www.bwsensing.com.domain.system.model.organization.StructureTemplate;
import www.bwsensing.com.domain.system.model.organization.SystemDeptTemplate;

/**
 * 系统组织结构模板领域网关
 * @author macos-zyj
 */
public interface SysStructureTemplateGateway {
    /**
     * 保存
     * @param structureTemplate
     */
    void save(StructureTemplate structureTemplate);

    /**
     * 修改
     * @param structureTemplate
     */
    void update(StructureTemplate structureTemplate);

    /**
     * 新增部门
     * @param deptTemplate
     */
    void addDepartment(SystemDeptTemplate deptTemplate);

    /**
     * 修改部门
     * @param deptTemplate
     */
    void updateDepartment(SystemDeptTemplate deptTemplate);

    /**
     * 删除部门
     * @param deptTemplate
     */
    void deleteDepartment(SystemDeptTemplate deptTemplate);

    /**
     * 根据编号获取组织结构模板
     * @param id
     * @return
     */
    StructureTemplate getStructureTemplateById(Integer id);
}
