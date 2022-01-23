package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.system.organization.SystemDept;
import www.bwsensing.com.domain.system.organization.SystemStructure;

/**
 * 系统组织结构 领域网关
 * @author macos-zyj
 */
public interface SystemStructureGateway {
    /**
     * 保存系统组织结构
     * @param systemStructure
     */
    void saveSystemStructure(SystemStructure systemStructure);

    /**
     * 添加部门
     * @param systemDept
     */
    void addDepartment(SystemDept systemDept);

    /**
     * 修改部门
     * @param systemDept
     */
    void updateDepartment(SystemDept systemDept);

    /**
     * 删除部门
     * @param id
     */
    void deleteDepartment(Integer id);

    /**
     * 根据Id获取对应的组织结构信息
     * @param id
     * @return
     */
    SystemStructure getSystemStructureById(Integer id);
}
