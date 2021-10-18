package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class SystemRoleCO extends DTO {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseRoleCode() {
        return baseRoleCode;
    }

    public void setBaseRoleCode(String baseRoleCode) {
        this.baseRoleCode = baseRoleCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getBaseRole() {
        return isBaseRole;
    }

    public void setBaseRole(Boolean baseRole) {
        isBaseRole = baseRole;
    }

    public Boolean getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Boolean roleStatus) {
        this.roleStatus = roleStatus;
    }
}
