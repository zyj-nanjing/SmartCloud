package www.bwsensing.com.domain.system.model.token;

import lombok.Data;
import java.util.List;
import www.bwsensing.com.domain.system.model.role.UserRole;

/**
 * @author macos-zyj
 */
@Deprecated
@Data
public class TokenData  {
    /**账户名称*/
    private String accountName;
    /**用户名**/
    private String userName;
    /**是否为管理员*/
    private Boolean isAdmin;
    /**角色*/
    private String role;
    /**用户编号*/
    private Integer userId;
    /**部门编号*/
    private Integer deptId;
    /**小组编号*/
    private Integer groupId;

    private List<UserRole> userRoles;
}
