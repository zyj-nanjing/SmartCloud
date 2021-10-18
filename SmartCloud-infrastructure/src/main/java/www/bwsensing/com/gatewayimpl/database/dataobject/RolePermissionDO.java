package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class RolePermissionDO {

    /**
     * 角色权限编号
     */
    private Integer id;

    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 权限编号
     */
    private String permissionId;

    /**
     * 角色权限状态
     */
    private String rolePermissionStatus;
}
