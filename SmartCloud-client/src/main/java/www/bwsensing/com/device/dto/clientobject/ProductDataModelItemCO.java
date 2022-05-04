package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductDataModelItemCO extends DTO {
    /**
     * 主键
     */
    private Integer id;

    private Integer modelId;

    /**
     * JSON key
     */
    private String keyName;

    /**
     * 编码类型
     */
    private Integer itemKind;


    /***
     * 唯一编码长度
     */
    private Integer uniqueCodeSize;

    /**
     * 监测因素编号
     */
    private Integer dataItemId;

    /**
     * 监测因素
     */
    private String itemName;

    /**
     * 数据位名称
     */
    private String dataName;

    /**
     * 数据位置
     */
    private Integer dataOrder;


    /**
     * 数据长度
     */
    private Integer dataLength;

    /**
     * 正则表达式
     */
    private String dataFormat;

    /**
     * 数据类型 换成枚举
     */
    private String dataType;

    /**
     * 是否需要转换
     */
    private Boolean needTransform;

    /**
     * 计算公式
     */
    private String calculationFormula;

    /**
     * 占位符
     */
    private String placeholder;

    public ProductDataModelItemCO() {
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

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Integer getItemKind() {
        return itemKind;
    }

    public void setItemKind(Integer itemKind) {
        this.itemKind = itemKind;
    }

    public Integer getUniqueCodeSize() {
        return uniqueCodeSize;
    }

    public void setUniqueCodeSize(Integer uniqueCodeSize) {
        this.uniqueCodeSize = uniqueCodeSize;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Integer getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(Integer dataOrder) {
        this.dataOrder = dataOrder;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getNeedTransform() {
        return needTransform;
    }

    public void setNeedTransform(Boolean needTransform) {
        this.needTransform = needTransform;
    }

    public String getCalculationFormula() {
        return calculationFormula;
    }

    public void setCalculationFormula(String calculationFormula) {
        this.calculationFormula = calculationFormula;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getDataItemId() {
        return dataItemId;
    }

    public void setDataItemId(Integer dataItemId) {
        this.dataItemId = dataItemId;
    }
}
