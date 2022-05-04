package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;

@SuppressWarnings("all")
public class ProductDataItemCO extends DTO {
    private static final long serialVersionUID = 21291L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 产品型号编号
     */
    private Integer modelId;
    /**
     * 名称
     */
    private String itemName;
    /**
     * 对应数据名称
     */
    private String dataId;
    /**
     * 单位
     */
    private String unit;

    /**
     * 小数点后长
     */
    private Integer decimalSize;

    /**
     * 数据项来源类型
     */
    private Integer itemKind;

    /**
     * 检测项属性类型
     */
    private Integer itemAttribute;

    /**
     * 是否需要转义
     */
    private Boolean needTransform;


    /**
     * 计算公式
     **/
    private String calculationFormula;

    /**
     * 占位符
     */
    private String placeholder;

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

    public Integer getDecimalSize() {
        return decimalSize;
    }

    public void setDecimalSize(Integer decimalSize) {
        this.decimalSize = decimalSize;
    }

    public Integer getItemKind() {
        return itemKind;
    }

    public void setItemKind(Integer itemKind) {
        this.itemKind = itemKind;
    }

    public Integer getItemAttribute() {
        return itemAttribute;
    }

    public void setItemAttribute(Integer itemAttribute) {
        this.itemAttribute = itemAttribute;
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
}
