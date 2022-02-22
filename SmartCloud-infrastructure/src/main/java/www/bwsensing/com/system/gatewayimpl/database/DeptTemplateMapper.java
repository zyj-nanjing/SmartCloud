package www.bwsensing.com.system.gatewayimpl.database;

import www.bwsensing.com.system.gatewayimpl.database.dataobject.SysDeptTemplateDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface DeptTemplateMapper {
    /**
     * 查询部门模板
     *
     * @param id 部门模板主键
     * @return 部门模板
     */
    SysDeptTemplateDO getDeptTemplateById(Integer id);

    /**
     * 查询部门模板列表
     *
     * @param sysDeptTemplateDO 部门模板
     * @return 部门模板集合
     */
    List<SysDeptTemplateDO> queryDeptTemplateBySort(SysDeptTemplateDO sysDeptTemplateDO);

    /**
     * 校验查询
     * @param validQuery
     * @return
     */
    List<SysDeptTemplateDO> validDeptTemplateBySort(SysDeptTemplateDO validQuery);
    /**
     * 新增部门模板
     *
     * @param sysDeptTemplateDO 部门模板
     * @return 结果
     */
    int addDeptTemplate(SysDeptTemplateDO sysDeptTemplateDO);

    /**
     * 修改部门模板
     *
     * @param sysDeptTemplateDO 部门模板
     * @return 结果
     */
    int updateDeptTemplate(SysDeptTemplateDO sysDeptTemplateDO);

    /**
     * 删除部门模板
     *
     * @param id 部门模板主键
     * @return 结果
     */
    int deleteById(Integer id);

    /**
     * 批量删除部门模板
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteByIds(String[] ids);
}
