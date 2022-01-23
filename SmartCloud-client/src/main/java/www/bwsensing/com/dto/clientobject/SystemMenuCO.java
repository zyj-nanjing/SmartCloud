package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class SystemMenuCO extends DTO {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**标题*/
    private String title;
    /**图标*/
    private String icon;
    /**是否为前端视图菜单*/
    private Boolean isView;
    /**视图类型*/
    private String menuKind;
    /**后端路由*/
    private String apiPath;
    /**权限标识*/
    private String roleCode;
    /**前端路径*/
    private String path;
    /**组件*/
    private String component;
    /**是否隐藏*/
    private Boolean hidden;
    /**是否长显示*/
    private Boolean alwaysShow;
    /**重定向地址*/
    private String redirect;
    /**是否为根节点*/
    private Boolean isBaseNode;
    /**上层节点*/
    private Integer upperNode;
    /**子节点*/
    private List<SystemMenuCO> children;
    private String creator;
    private Date createTime;
    private String updater;
    private Date updateTime;

}
