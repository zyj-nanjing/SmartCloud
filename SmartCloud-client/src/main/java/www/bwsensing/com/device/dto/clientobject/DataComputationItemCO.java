package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class DataComputationItemCO extends DTO {
    private String prefix;
    /**
     * 对应的数据相位的类型
     */
    private Integer itemKind;

    private Integer currentId;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getItemKind() {
        return itemKind;
    }

    public void setItemKind(Integer itemKind) {
        this.itemKind = itemKind;
    }

    public Integer getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Integer currentId) {
        this.currentId = currentId;
    }
}
