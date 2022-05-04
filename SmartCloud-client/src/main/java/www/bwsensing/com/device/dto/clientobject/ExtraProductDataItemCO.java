package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ExtraProductDataItemCO extends DTO {

    private static final long serialVersionUID = 11291L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 产品型号编码
     */
    private Integer modelId;

    /**
     * 额外数据项名称
     */
    private String  extraItemName;

    /**
     * 额外数据编码
     */
    private String extraDataId;

    /***
     * 额外数据
     */
    private String extraData;

    /**
     * 单位
     */
    private String unit;

    /**
     * 是否需要转换
     */
    private Boolean needTransform;

    /**
     * 转换公式
     */
    private String calculationFormula;

    /**
     * 占位符
     */
    private String placeholder;

    public ExtraProductDataItemCO() {
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

    public String getExtraItemName() {
        return extraItemName;
    }

    public void setExtraItemName(String extraItemName) {
        this.extraItemName = extraItemName;
    }

    public String getExtraDataId() {
        return extraDataId;
    }

    public void setExtraDataId(String extraDataId) {
        this.extraDataId = extraDataId;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
