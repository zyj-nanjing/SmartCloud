package www.bwsensing.com.domain.system;

import com.alibaba.cola.extension.BizScenario;
import lombok.Data;
import java.util.Date;

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
    /**用户角色**/
    private UserRole userRole;
    /**用例场景*/
    private String scenario;
    /**业务线编号**/
    private String bizId;
    private BizScenario bizScenario;
    private boolean enabled;
    /**账户是否过期**/
    private boolean accountNonExpired;
    /**账户是否被锁**/
    private boolean accountNonLocked;
    private Date lastLoginTime;
}
