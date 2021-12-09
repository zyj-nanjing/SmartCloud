package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class UserLoginCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = -4866964695692375080L;
    @NotBlank(message = "用户名不能为空!")
    private String accountName;
    @NotBlank(message="登录密码不能为空!")
    private String password;
}
