package www.bwsensing.com.monitor.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import www.bwsensing.com.monitor.dto.clientobject.MonitorItemsCO;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 检测原型
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class PrototypeCO extends DTO {
    /**主键*/
    private Integer id;
    /**类型名称*/
    @NotNull
    private String  typeName;
    private String itemShow;
    private List<MonitorItemsCO>  itemsList;
    /**创建人**/
    private String creator;
    /**创建时间**/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**修改者**/
    private String updater;
    /**修改时间**/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date  updateTime;
    /**是否有效**/
    private boolean isEffective;
    private Integer orderSort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getItemShow() {
        return itemShow;
    }

    public void setItemShow(String itemShow) {
        this.itemShow = itemShow;
    }

    public List<MonitorItemsCO> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<MonitorItemsCO> itemsList) {
        this.itemsList = itemsList;
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

    public boolean isEffective() {
        return isEffective;
    }

    public void setEffective(boolean effective) {
        isEffective = effective;
    }

    public Integer getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(Integer orderSort) {
        this.orderSort = orderSort;
    }
}
