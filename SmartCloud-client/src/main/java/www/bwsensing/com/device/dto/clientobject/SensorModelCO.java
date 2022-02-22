package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import www.bwsensing.com.monitor.dto.clientobject.PrototypeCO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class SensorModelCO extends DTO {
    /**主键**/
    private Integer id;
    /**模型名称*/
    private String modelName;
    /**模型类型**/
    private String modelKind;
    /**型号图片*/
    private String picture;
    /**说明**/
    private String comment;
    /**监控类型*/
    private List<PrototypeCO> prototypeList;
    /**版本*/
    private Double version;
    /**创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**是否有效**/
    private Boolean isEffective;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelKind() {
        return modelKind;
    }

    public void setModelKind(String modelKind) {
        this.modelKind = modelKind;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<PrototypeCO> getPrototypeList() {
        return prototypeList;
    }

    public void setPrototypeList(List<PrototypeCO> prototypeList) {
        this.prototypeList = prototypeList;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getEffective() {
        return isEffective;
    }

    public void setEffective(Boolean effective) {
        isEffective = effective;
    }
}
