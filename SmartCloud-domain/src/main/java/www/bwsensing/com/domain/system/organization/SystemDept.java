package www.bwsensing.com.domain.system.organization;

import lombok.Data;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class SystemDept {
    /** 部门ID */
    private Integer id;

    /**组织结构编号*/
    private Integer structureId;

    /** 父部门ID */
    private Integer parentId;

    /** 子类部门 */
    private List<SystemDept> childrenDept;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private Integer orderNumber;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private DeptStatus status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 父部门名称 */
    private String parentName;

    /** 排除编号 */
    private Long excludeId;
}
