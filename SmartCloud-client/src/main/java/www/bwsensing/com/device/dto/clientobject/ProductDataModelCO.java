package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductDataModelCO extends DTO {
    /**
     * 主键
     */
    private Integer id;

    private Integer modelId;

    /**
     * 数据形式
     */
    private Integer dataForm;

    /**
     * 名称
     */
    private String name;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 进制
     */
    private Integer carrySystem;

    private Integer expectDataSize;
    /**
     * 分隔方式(枚举)
     */
    private Integer splitMethod;

    /**
     * 排序
     */
    private Integer weight;

    /**
     * 分隔符
     */
    private String separator;

    /**
     * 基础数据单元长度
     */
    private Integer baseDataSize;

    /**
     * 数据项
     */
    private List<ProductDataModelItemCO> dataItems;

    /**
     * 对应数据格式的正则
     */
    private String dataFormat;

    /**
     * 备注
     */
    private String remark;

    public ProductDataModelCO() {
    }

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

    public Integer getDataForm() {
        return dataForm;
    }

    public void setDataForm(Integer dataForm) {
        this.dataForm = dataForm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getCarrySystem() {
        return carrySystem;
    }

    public void setCarrySystem(Integer carrySystem) {
        this.carrySystem = carrySystem;
    }

    public Integer getExpectDataSize() {
        return expectDataSize;
    }

    public void setExpectDataSize(Integer expectDataSize) {
        this.expectDataSize = expectDataSize;
    }

    public Integer getSplitMethod() {
        return splitMethod;
    }

    public void setSplitMethod(Integer splitMethod) {
        this.splitMethod = splitMethod;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public Integer getBaseDataSize() {
        return baseDataSize;
    }

    public void setBaseDataSize(Integer baseDataSize) {
        this.baseDataSize = baseDataSize;
    }

    public List<ProductDataModelItemCO> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<ProductDataModelItemCO> dataItems) {
        this.dataItems = dataItems;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
