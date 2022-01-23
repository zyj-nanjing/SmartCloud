package www.bwsensing.com.dto.command;

import lombok.Data;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class DeptTemplateSaveCmd extends CommonCommand implements Serializable {
    /** 组织编号 */
    @NotNull(message = "组织编号不能为空!")
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
    @NotBlank(message = "部门名称不能为空!")
    private String deptName;

    /** 排除编号 */
    private Long excludeId;
}
