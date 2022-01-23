package www.bwsensing.com.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.SysStructureTemplateDO;
import java.util.List;

/**
 * 系统组织结构模板
 * @author macos-zyj
 */
public interface StructureTemplateMapper {
    /**
     * 查询系统组织模板
     *
     * @param id 系统组织模板主键
     * @return 系统组织模板
     */
    SysStructureTemplateDO getStructureTemplateById(Integer id);

    /**
     * 查询系统组织模板列表
     *
     * @param sysStructureTemplateDO 系统组织模板
     * @return 系统组织模板集合
     */
    List<SysStructureTemplateDO> queryStructureTemplateBySort(SysStructureTemplateDO sysStructureTemplateDO);

    /**
     * 查询系统组织模板列表
     *
     * @param validQuery 系统组织模板
     * @return 系统组织模板集合
     */
    List<SysStructureTemplateDO> validStructureTemplateBySort(SysStructureTemplateDO validQuery);

    /**
     * 新增系统组织模板
     *
     * @param sysStructureTemplateDO 系统组织模板
     * @return 结果
     */
    int addStructureTemplate(SysStructureTemplateDO sysStructureTemplateDO);

    /**
     * 修改系统组织模板
     *
     * @param sysStructureTemplateDO 系统组织模板
     * @return 结果
     */
    int updateStructureTemplate(SysStructureTemplateDO sysStructureTemplateDO);

    /**
     * 删除系统组织模板
     *
     * @param id 系统组织模板主键
     * @return 结果
     */
    int deleteById(Integer id);

    /**
     * 批量删除系统组织模板
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int  deleteByIds(String[] ids);

    /**
     * 添加组织结构与行业领域关联
     * @param tempId
     * @param filedId
     * @return
     */
    int addStructureTemplateFieldLink(@Param("tempId")Integer tempId, @Param("filedId")Integer filedId);

    /**
     * 删除组织结构目标与行业领域关联
     * @param templateId
     * @return
     */
    int deleteStructureTemplateFieldLink(Integer templateId);

}
