package www.bwsensing.com.dto.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author macos-zyj
 */
@Data
public class DeptTemplateUpdateCmd extends CommonCommand implements Serializable {
    /** 主键 */
    @NotNull(message = "主键不能为空!")
    private Integer id;

    /** 组织编号 */
    @NotNull(message = "组织编号不能为空!")
    private Long structureId;

    /**父节点编号*/
    private Integer parentId;

    /**祖级列表*/
    private String ancestors;

    /** 显示顺序 */
    private String orderNum;

    /** 父部门名称 */
    private String parentName;

    /** 部门名称 */
    @NotBlank(message = "部门名称不能为空!")
    private String deptName;

    /** 排除编号 */
    private Long excludeId;
}
