package www.bwsensing.com.system.gatewayimpl.database;

import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemDeptDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface SystemDeptMapper {
    /**
     * 查询系统部门
     *
     * @param id 系统部门主键
     * @return 系统部门
     */
    SystemDeptDO getSystemDeptById(Integer id);

    /**
     * 查询系统部门列表
     *
     * @param systemDeptDO 系统部门
     * @return 系统部门集合
     */
    List<SystemDeptDO> selectSystemDeptBySort(SystemDeptDO systemDeptDO);


    /**
     * 查询系统部门列表
     *
     * @param validQuery 系统部门
     * @return 系统部门集合
     */
    List<SystemDeptDO> validDeptBySort(SystemDeptDO validQuery);


    /**
     * 根据组织结构编号获取集合
     * @param structureId
     * @return
     */
    List<SystemDeptDO> getDepartmentByStructureId(Integer structureId);

    /**
     * 新增系统部门
     *
     * @param systemDeptDO 系统部门
     * @return 结果
     */
    int addSystemDept(SystemDeptDO systemDeptDO);

    /**
     * 修改系统部门
     *
     * @param systemDeptDO 系统部门
     * @return 结果
     */
    int updateSystemDept(SystemDeptDO systemDeptDO);

    /**
     * 删除系统部门
     *
     * @param id 系统部门主键
     * @return 结果
     */
    int deleteById(Integer id);

    /**
     * 批量删除系统部门
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteByIds(String[] ids);
}
