package www.bwsensing.com.domain.system.token;

import lombok.Data;
import www.bwsensing.com.domain.system.user.SystemUser;
import www.bwsensing.com.domain.system.organization.SystemDept;

/**
 * @author macos-zyj
 */
@Data
public class UserInfo {
    /**
     * 用户基本信息
     */
    private SystemUser systemUser;

    /**
     * 是否为管理员
     */
    private Boolean isAdmin;

    /**
     * 权限集合
     */
    private String[] permissions;

    /**
     * 所属部门
     */
    private SystemDept department;

    /**
     * 角色集合
     */
    private Integer[] userRoles;
}
