package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("all")
public class SystemDeptCO  extends DTO {
    /** 主键 */
    private Integer id;

    /** 组织编号 */
    private Integer structureId;

    /**父节点编号*/
    private Integer parentId;

    /**子节点*/
    private List<SystemDeptCO>  childrenDept;

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
}
