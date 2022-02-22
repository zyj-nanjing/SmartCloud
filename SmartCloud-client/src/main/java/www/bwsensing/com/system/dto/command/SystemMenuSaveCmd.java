package www.bwsensing.com.system.dto.command;

import com.alibaba.cola.dto.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
@ApiModel(value="菜单新增",description="菜单新增")
public class SystemMenuSaveCmd extends Command {
    /**名称*/
    @ApiModelProperty(name = "菜单名称",example="systemManger",required = true)
    @NotBlank( message ="菜单名称不能为空!")
    private String name;
    /**标题*/
    @ApiModelProperty(name ="菜单标题",example="系统管理",required = true)
    @NotBlank( message ="菜单标题不能为空!")
    private String title;
    /**图标*/
    @ApiModelProperty(name = "菜单图标",example="menu")
    private String icon;
    /**是否为前端视图菜单*/
    @ApiModelProperty(name = "是否为前端接口",example= "true",required = true)
    @NotNull(message="是否为前端接口不能为空!")
    private Boolean isView;
    /**视图类型*/
    @ApiModelProperty(name = "视图类别",example= "F",required = true)
    @NotBlank(message="视图类型不能为空!")
    private String menuKind;
    /**后端路由*/
    @ApiModelProperty(name = "后端接口路径",example= "/user/login")
    private String apiPath;
    /**权限标识*/
    @ApiModelProperty(name = "权限编码",example= "SYS_MENU")
    @NotBlank( message ="权限编码不能为空!")
    private String roleCode;
    /**前端路径*/
    @ApiModelProperty(name = "前端路径",example= "menu")
    private String path;
    /**组件*/
    @ApiModelProperty(name = "前端组件",example= "menu")
    private String component;
    /**是否隐藏*/
    @ApiModelProperty(name = "是否隐藏",example= "true")
    private Boolean hidden;
    /**是否长显示*/
    @ApiModelProperty(name = "是否长显示",example= "true")
    private Boolean alwaysShow;
    /**重定向地址*/
    @ApiModelProperty(name = "重定向地址")
    private String redirect;
    /**是否为根节点*/
    @ApiModelProperty(name = "是否为根节点",required=true)
    @NotNull( message ="是否为根节点不能为空!")
    private Boolean isBaseNode;
    /**上层节点*/
    @ApiModelProperty(name = "上层节点")
    private Integer upperNode;
    /** 显示顺序 */
    @ApiModelProperty(name = "显示顺序")
    private Integer orderNum;
    private String creator;
    private Date createTime;
}
