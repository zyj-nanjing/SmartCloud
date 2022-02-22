package www.bwsensing.com.system.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

import java.util.List;

/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class DeptTemplateCO extends DTO {
    /** 部门ID */
    private Integer id;

    /** 父部门ID */
    private Integer parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private String orderNum;

    /** 父部门名称 */
    private String parentName;

    /** 排除编号 */
    private Long excludeId;

    private List<DeptTemplateCO> children;
}
