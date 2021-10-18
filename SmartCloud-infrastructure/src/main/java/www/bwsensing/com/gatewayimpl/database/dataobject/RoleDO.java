package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
/**
 * @author macos-zyj
 */
@Data
public class RoleDO {
    /**主键*/
    private Integer id;
    /**主要层级的权限编码**/
    private String baseRoleCode;
    /**权限编码**/
    private String roleCode;
    /**角色名称**/
    private String roleName;
    /**是否是根权限**/
    private Boolean isBaseRole;
    /**角色状态，0正常，-1删除**/
    private Boolean roleStatus;
}
