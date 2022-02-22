package www.bwsensing.com.system.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class PermissionSaveCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = -7006689807130902256L;
    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空!")
    private String name;
    /**
     * 授权url
     */
    @NotBlank(message = "权限路由不能为空!")
    private String url;

    /**
     * 描述
     *
     */
    private String comment;
}
