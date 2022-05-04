package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import www.bwsensing.com.monitor.dto.clientobject.IndustryFieldCO;
import www.bwsensing.com.monitor.dto.clientobject.PrototypeCO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductModelCO extends DTO {
    /**主键**/
    private Integer id;
    /**模型名称*/
    private String productName;
    /**模型类型**/
    private Integer productKindId;
    /**型号图片*/
    private String picture;
    /**产品厂商*/
    private Integer manufacturerId;
    /**说明**/
    private String comment;
    /**
     * 直接给到厂家网站路由或商品链接
     */
    private String productDetails;
    /**
     * 交互方式
     */
    private String interactionMode;

    /**版本*/
    private Double version;
    /**
     * 所属行业领域
     */
    private List<IndustryFieldCO> industryFields;
    /**创建者*/
    private String creator;
    /**创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**修改者*/
    private String updater;
    /**修改时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    /**是否有效**/
    private Boolean isEffective;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductKindId() {
        return productKindId;
    }

    public void setProductKindId(Integer productKindId) {
        this.productKindId = productKindId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getInteractionMode() {
        return interactionMode;
    }

    public void setInteractionMode(String interactionMode) {
        this.interactionMode = interactionMode;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public List<IndustryFieldCO> getIndustryFields() {
        return industryFields;
    }

    public void setIndustryFields(List<IndustryFieldCO> industryFields) {
        this.industryFields = industryFields;
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

    public Boolean getEffective() {
        return isEffective;
    }

    public void setEffective(Boolean effective) {
        isEffective = effective;
    }
}
