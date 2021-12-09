package www.bwsensing.com.dto.command;

import com.alibaba.cola.extension.BizScenario;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * @author macos-zyj
 */
@Data
public class UserRegisterCmd extends CommonCommand implements Serializable {
    private static final long serialVersionUID = 1L;

    /**用户名称*/
    @NotBlank(message = "用户名称能为空")
    private String userName;
    /**分组编号**/
    @NotNull(message = "分组编号不能为空")
    private Integer groupId;
    @NotBlank(message = "账户名称能为空")
    private String accountName;
    /**工号**/
    @NotBlank(message = "工号不能为空")
    private String workNumber;
    /** 手机号 */
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    /** 邮件 */
    @NotBlank(message = "邮件地址不能为空")
    private String email;
    /** 密码 */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**必须为 isAdmin True 且分组为内部分组才给予管理员权限**/
    private Boolean isAdmin;
    /**用例场景*/
    private String scenario;

    private String bizId;
    private BizScenario bizScenario;
}