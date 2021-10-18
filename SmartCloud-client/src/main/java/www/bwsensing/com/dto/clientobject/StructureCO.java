package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class StructureCO extends DTO {
    /**主键*/
    private Integer id;
    /**模型编号**/
    private Integer modelId;
    /**名称*/
    private String name;
    /**图片*/
    private String picture;
    /**结构物名称**/
    private String structureName;
    /**描述*/
    private String comment;
    /**当前结构物版本**/
    private Double currentVersion;
    /**线上版本*/
    private Double onlineVersion;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**测点合集*/
    private List<SensorPositionCO> positionList;
    /**创建人*/
    private String creator;
    /**创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**修改人*/
    private String updater;
    /**修改时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Double currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Double getOnlineVersion() {
        return onlineVersion;
    }

    public void setOnlineVersion(Double onlineVersion) {
        this.onlineVersion = onlineVersion;
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

    public List<SensorPositionCO> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<SensorPositionCO> positionList) {
        this.positionList = positionList;
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
