package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class RoleSaveCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = 19104887074678285L;
    /** 主要层级的权限编码 **/
    private String baseRoleCode;
    /**权限编码**/
    @NotBlank(message = "权限编码不能为空")
    private String roleCode;
    /**角色名称**/
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**是否是根权限**/
    @NotNull(message = "是否为根角色不能为空")
    private Boolean isBaseRole;
}
