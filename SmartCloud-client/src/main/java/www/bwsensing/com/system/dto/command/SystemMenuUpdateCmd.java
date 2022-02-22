package www.bwsensing.com.system.dto.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author macos-zyj
 */
@ApiModel(description="菜单修改")
@Data
public class SystemMenuUpdateCmd extends Command {
    @ApiModelProperty(name ="主键",example = "1",required = true)
    @NotNull(message="菜单编号不能为空!")
    private Integer id;
    /**名称*/
    @ApiModelProperty(name ="菜单名称",required = true)
    @NotBlank( message ="菜单名称不能为空!")
    private String name;
    /**标题*/
    @ApiModelProperty(name ="菜单标题",required = true)
    @NotBlank( message ="菜单标题不能为空!")
    private String title;
    /**图标*/
    @ApiModelProperty(name ="图标",required = true)
    private String icon;
    /**是否为前端视图菜单*/
    @NotNull(message="菜单类别不能为空!")
    @ApiModelProperty(name ="是否为前端视图",required = true)
    private Boolean isView;
    /**视图类型*/
    @ApiModelProperty(value = "视图类别",example= "F",required = true)
    @NotNull(message="视图类型不能为空!")
    private String menuKind;
    /**后端路由*/
    @ApiModelProperty(name ="后端路由路径")
    private String apiPath;
    /**权限标识*/
    @ApiModelProperty(name ="权限编码",required = true)
    @NotBlank( message ="权限编码不能为空!")
    private String roleCode;
    /**前端路径*/
    @ApiModelProperty(name ="前端路径")
    private String path;
    /**组件*/
    @ApiModelProperty(name ="组件")
    private String component;
    /**是否隐藏*/
    @ApiModelProperty(name ="是否隐藏")
    private Boolean hidden;
    /**是否长显示*/
    @ApiModelProperty(name ="是否长显")
    private Boolean alwaysShow;
    /**重定向地址*/
    @ApiModelProperty(name ="重定向地址")
    private String redirect;
    /**是否为根节点*/
    @ApiModelProperty(name ="是否为根节点",required = true)
    @NotNull( message ="是否为根节点不能为空!")
    private Boolean isBaseNode;
    /**上层节点*/
    @ApiModelProperty(value = "上层节点")
    private Integer upperNode;
    /** 显示顺序 */
    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;
    private String update;
    private Date updateTime;
}
