package www.bwsensing.com.system.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class SystemRoleCO extends DTO {
    private Integer id;
    /**权限编码**/
    private String roleCode;
    /**角色名称**/
    private String roleName;
    /**角色排序*/
    private Integer roleSort;
    /**数据范围*/
    private String dataScope;
    /**角色状态，0正常，-1删除**/
    private Boolean roleStatus;
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;
    /** 菜单组 */
    private List<SystemMenuCO> systemMenus;
    /** 部门组（数据权限） */
    private Long[] deptIds;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改人*/
    private String updater;
    /**修改时间*/
    private Date updateTime;
}
