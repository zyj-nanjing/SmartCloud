package www.bwsensing.com.system.gatewayimpl.database.dataobject;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 部门模板对象 sys_dept_template
 *
 * @author mac_zyj
 * @date 2022-01-13
 */
@Data
public class SysDeptTemplateDO {
    /** 主键 */
    private Integer id;

    /** 组织编号 */
    private Integer structureId;

    /**父节点编号*/
    private Integer parentId;

    /**祖级列表*/
    private String ancestors;

    /** 显示顺序 */
    private String orderNum;

    /** 父部门名称 */
    private String parentName;

    /** 部门名称 */
    private String deptName;

    /** 排除编号 */
    private Long excludeId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("structureId", getStructureId())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("deptName", getDeptName())
                .append("orderNum", getOrderNum())
                .append("parentName", getParentName())
                .append("excludeId", getExcludeId())
                .toString();
    }
}
