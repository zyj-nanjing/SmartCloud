package www.bwsensing.com.common.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class DataItemsCO extends DTO {

    private Integer id;

    /**
     * 数据项名称
     **/
    private String itemName;

    /**
     * 数据项编码
     **/
    private String dataId;

    /**
     * 数据单位
     **/
    private String unit;

    public DataItemsCO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
