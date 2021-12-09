package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author macos-zyj
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("all")
public class ViewMenuTreeCO extends DTO {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
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
    private ViewMetaCO meta;
    private List<ViewMenuTreeCO> children;
}
