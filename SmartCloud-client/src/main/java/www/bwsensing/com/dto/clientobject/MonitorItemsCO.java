package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * 检测项
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class MonitorItemsCO extends DTO {
    private Integer id;
    /**名称**/
    private String itemName;
    /**对应数据名称**/
    private String dataId;
    /**单位**/
    private String unit;
    /**ASCII 解析标位*/
    private Integer asciiIndex;
    /**HEX 解析标位*/
    private Integer hexIndex;
    /**ASCII 解析前缀*/
    private String asciiPrefix;
    /**HEX减 偏移量*/
    private Integer  reduceNumber;
    /**HEX除 偏移量*/
    private Integer  divideNumber;

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

    public Integer getAsciiIndex() {
        return asciiIndex;
    }

    public void setAsciiIndex(Integer asciiIndex) {
        this.asciiIndex = asciiIndex;
    }

    public Integer getHexIndex() {
        return hexIndex;
    }

    public void setHexIndex(Integer hexIndex) {
        this.hexIndex = hexIndex;
    }

    public String getAsciiPrefix() {
        return asciiPrefix;
    }

    public void setAsciiPrefix(String asciiPrefix) {
        this.asciiPrefix = asciiPrefix;
    }

    public Integer getReduceNumber() {
        return reduceNumber;
    }

    public void setReduceNumber(Integer reduceNumber) {
        this.reduceNumber = reduceNumber;
    }

    public Integer getDivideNumber() {
        return divideNumber;
    }

    public void setDivideNumber(Integer divideNumber) {
        this.divideNumber = divideNumber;
    }
}
