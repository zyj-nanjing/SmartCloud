package www.bwsensing.com.system.dto.command;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.alibaba.cola.dto.Command;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class SystemRoleUpdateCmd extends Command {
    private static final long serialVersionUID = 2155307829206403995L;
    /**主键*/
    @ApiModelProperty(value = "主键",required=true)
    @NotNull(message = "主键不能为空!")
    private Integer id;
    /**权限编码**/
    @ApiModelProperty(value = "权限编码",required=true)
    @NotBlank(message = "权限编码不能为空")
    private String roleCode;
    /**角色名称**/
    @ApiModelProperty(value = "角色名称",required=true)
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**角色排序*/
    @ApiModelProperty(value = "排序")
    private Integer roleSort = 0;
    /**数据范围*/
    @ApiModelProperty(value = "数据范围",required=true)
    @NotBlank(message = "数据范围不能为空")
    private String dataScope;
    /**菜单编号集*/
    @ApiModelProperty(value = "菜单列表",required=true)
    @Valid
    @NotNull(message = "菜单列表不能为空")
    @Size(min = 1, message = "列表至少要有一个菜单")
    private List<Integer> menuIds;
}
