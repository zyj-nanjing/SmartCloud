package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统部门对象 sys_dept
 *
 * @author mac_zyj
 * @date 2022-01-13
 */
@Data
public class SystemDeptDO {
    /** 主键 */
    private Integer id;

    /** 组织编号 */
    private Integer structureId;

    /**父节点编号*/
    private Integer parentId;

    /**祖级列表*/
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private Long orderNumber;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态 */
    private Integer status;

    private String parentName;

    /** 排除编号 */
    private Integer excludeId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("structureId", getStructureId())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("deptName", getDeptName())
                .append("orderNumber", getOrderNumber())
                .append("leader", getLeader())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("status", getStatus())
                .append("excludeId", getExcludeId())
                .toString();
    }
}
