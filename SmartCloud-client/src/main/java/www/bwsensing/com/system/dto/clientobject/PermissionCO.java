package www.bwsensing.com.system.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class PermissionCO extends DTO {
    private Integer id;
    private String permissionUrl;
    /**
     * 角色名称
     */
    private List<String> roleNames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
