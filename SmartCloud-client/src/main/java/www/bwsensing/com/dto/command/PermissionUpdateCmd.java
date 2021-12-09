package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class PermissionUpdateCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = 5103633747286416076L;
    /**
     * 权限编号
     */
    @NotNull(message="主键不能为空")
    private Integer id;
    /**
     * 权限名称
     */
    private String name;

    /**
     * 授权url
     */
    private String url;

    /**
     * 描述
     *
     */
    private String comment;

    /**
     * 权限状态,0正常，-1删除
     */
    private Boolean status;
}
