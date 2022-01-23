package www.bwsensing.com.domain.system.user;

import com.alibaba.cola.extension.BizScenario;
import lombok.Data;
import www.bwsensing.com.domain.system.role.UserRole;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class SystemUser {
    private Integer id;
    /**用户名**/
    private String userName;
    /**账户名称*/
    private String accountName;
    /**工号**/
    private String workNumber;
    /** 头像 */
    private String avatar;
    /**手机号**/
    private String mobile;
    /**邮箱*/
    private String email;
    /**密码**/
    private String password;
    /** 职位 */
    private String position;
    /**分组编号**/
    private Integer groupId;
    /**操作组**/
    private String operateGroup;
    /**角色*/
    private String role;
    /***
     * 所属角色列表
     */
    private List<UserRole> roles;

    /**是否允许通知*/
    private Boolean enableNotification;
    /**用户角色**/
    private UserRole userRole;
    /**是否为管理员*/
    private Boolean isAdmin;
    /**用例场景*/
    private String scenario;
    /**业务线编号**/
    private String bizId;
    private BizScenario bizScenario;
    private Boolean enabled;
    /**账户是否过期**/
    private Boolean accountNonExpired;
    /**账户是否被锁**/
    private Boolean accountNonLocked;
    private Date lastLoginTime;
}
