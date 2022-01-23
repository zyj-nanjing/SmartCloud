package www.bwsensing.com.domain.system.organization;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author macos-zyj
 */
@Data
public class SystemDeptTemplate {
    /** 部门ID */
    private Integer id;
    /**组织结构编号*/
    private Integer structureId;
    /** 父部门ID */
    private Integer parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private Integer orderNum;

    /** 父部门名称 */
    private String parentName;

    /** 排除编号 */
    private Long excludeId;

    public SystemDept initSystemDept(){
        SystemDept dept = new SystemDept();
        dept.setId(id);
        dept.setParentId(parentId);
        dept.setAncestors(ancestors);
        dept.setDeptName(deptName);
        dept.setOrderNumber(orderNum);
        dept.setExcludeId(excludeId);
        return dept;
    }
}
