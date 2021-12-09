package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class PositionModelCO extends DTO {
    /**主键*/
    private Integer id;
    /**位置名称**/
    private String name;
    /**点位说明**/
    private String comment;
    /**版本**/
    private Double version;
    /**结构体版本*/
    private Double structureVersion;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**创建者*/
    private String creator;
    /***创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Double getStructureVersion() {
        return structureVersion;
    }

    public void setStructureVersion(Double structureVersion) {
        this.structureVersion = structureVersion;
    }

    public Integer getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(Integer orderSort) {
        this.orderSort = orderSort;
    }

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
