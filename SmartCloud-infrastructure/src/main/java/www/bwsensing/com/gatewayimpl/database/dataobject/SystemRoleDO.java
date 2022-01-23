package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;
/**
 * @author macos-zyj
 */
@Data
public class SystemRoleDO {
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
    private Boolean flag ;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改人*/
    private String updater;
    /**修改时间*/
    private Date updateTime;
}
