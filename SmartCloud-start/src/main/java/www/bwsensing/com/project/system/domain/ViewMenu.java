package www.bwsensing.com.project.system.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 前端界面配置
 * @author macos-zyj
 */
@Data
public class ViewMenu {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**标题*/
    private String title;
    /**图标*/
    private String icon;
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
    /**子菜单*/
    private List<ViewMenu> children;
    private String creator;
    private Date createTime;
    private String updater;
    private Date updateTime;
}
