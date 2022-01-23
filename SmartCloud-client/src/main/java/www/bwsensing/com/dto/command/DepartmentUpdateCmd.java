package www.bwsensing.com.dto.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class DepartmentUpdateCmd extends CommonCommand{

    @NotNull(message = "主键不能为空")
    private Integer id;

    /**组织结构编号*/
    @NotNull(message = "所属组织结构编号不能为空")
    private Integer structureId;

    /** 父部门ID */
    private Integer parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    @NotBlank(message = "部门名称不能为空")
    private String deptName;

    /** 显示顺序 */
    private Integer orderNumber;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 排除编号 */
    private Long excludeId;
}
