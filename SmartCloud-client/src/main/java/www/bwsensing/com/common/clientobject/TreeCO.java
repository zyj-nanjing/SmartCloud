package www.bwsensing.com.common.clientobject;

import com.alibaba.cola.dto.DTO;

import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class TreeCO extends DTO {
    /**主键*/
    private Integer id;
    /**值*/
    private String value;
    /**标签*/
    private String lable;
    private List<TreeCO> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public List<TreeCO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeCO> children) {
        this.children = children;
    }
}
