package www.bwsensing.com.system.dto.command;

import com.alibaba.cola.dto.Command;
import com.alibaba.cola.extension.BizScenario;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class UserUpdateCmd extends Command {
    /**主键*/
    @NotNull(message ="用户编号不能为空!")
    private Integer id;
    /**用户名*/
    private String userName;
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
    /**角色*/
    private String role;
    /**是否允许通知*/
    private Boolean enableNotification;
    /**用例场景*/
    private String scenario;
    private String bizId;
    private BizScenario bizScenario;
}
