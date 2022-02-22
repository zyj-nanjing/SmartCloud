package www.bwsensing.com.domain.system.gateway;

import www.bwsensing.com.domain.system.model.organization.SystemDept;
import www.bwsensing.com.domain.system.model.organization.SystemStructure;
import www.bwsensing.com.domain.system.model.user.SystemUser;

import java.util.List;

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
     * 添加团队人员
     * @param organization
     * @param member
     */
    void addCompanyMember(SystemStructure organization, SystemUser member);

    /**
     * 往团队添加用户
     * @param organization 对应的组织结构
     * @param members 需要添加的人员
     */
    void addCompanyMembers(SystemStructure organization, List<SystemUser> members);

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

    /**
     * 根据Code获取组织
     * @param code
     * @return
     */
    SystemStructure getOrganizationByCode(String code);
}
