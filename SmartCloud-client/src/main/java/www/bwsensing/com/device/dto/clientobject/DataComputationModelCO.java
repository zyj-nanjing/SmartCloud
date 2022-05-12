package www.bwsensing.com.device.dto.clientobject;

import java.util.List;
import com.alibaba.cola.dto.DTO;
/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class DataComputationModelCO extends DTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 计算模型名称
     */
    private String name;

    /**
     * 产品型号Id
     */
    private Integer modelId;

    /**
     * 数据项Id
     */
    private Integer dataItemId;

    /**
     * 计算数据编码
     */
    private String computationDataId;

    /**
     * 产品数据项
     */
    private ProductDataItemCO productDataItem;
    /**
     * 计算触发类型
     */
    private Integer computationKind;

    /**
     * 公式名称
     */
    private String formulaName;

    /**
     * 计算公式
     */
    private String computationFormula;

    private List<DataComputationItemCO> computationItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getDataItemId() {
        return dataItemId;
    }

    public void setDataItemId(Integer dataItemId) {
        this.dataItemId = dataItemId;
    }

    public String getComputationDataId() {
        return computationDataId;
    }

    public void setComputationDataId(String computationDataId) {
        this.computationDataId = computationDataId;
    }

    public ProductDataItemCO getProductDataItem() {
        return productDataItem;
    }

    public void setProductDataItem(ProductDataItemCO productDataItem) {
        this.productDataItem = productDataItem;
    }


    public Integer getComputationKind() {
        return computationKind;
    }

    public void setComputationKind(Integer computationKind) {
        this.computationKind = computationKind;
    }

    public String getComputationFormula() {
        return computationFormula;
    }

    public void setComputationFormula(String computationFormula) {
        this.computationFormula = computationFormula;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public List<DataComputationItemCO> getComputationItems() {
        return computationItems;
    }

    public void setComputationItems(List<DataComputationItemCO> computationItems) {
        this.computationItems = computationItems;
    }
}
