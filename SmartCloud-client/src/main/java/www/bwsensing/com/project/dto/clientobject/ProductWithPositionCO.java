package www.bwsensing.com.project.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductWithPositionCO extends DTO {
    /**主键*/
    private Integer id;
    /**结构物标识编码*/
    /**传感器编码*/
    private String sensorSn;
    /**位置名称**/
    private String name;
    /**点位说明**/
    private String comment;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**绑定状态**/
    private Integer bindingStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorSn() {
        return sensorSn;
    }

    public void setSensorSn(String sensorSn) {
        this.sensorSn = sensorSn;
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

    public Integer getBindingStatus() {
        return bindingStatus;
    }

    public void setBindingStatus(Integer bindingStatus) {
        this.bindingStatus = bindingStatus;
    }
}
