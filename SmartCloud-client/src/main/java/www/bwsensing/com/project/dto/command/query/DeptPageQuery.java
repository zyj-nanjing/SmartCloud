package www.bwsensing.com.project.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class DeptPageQuery extends PageQuery {
    /** 组织编号 */
    @NotNull(message = "组织编号不能为空!")
    private Integer structureId;
    /** 父部门ID */
    private Integer parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 父部门名称 */
    private String parentName;
}
