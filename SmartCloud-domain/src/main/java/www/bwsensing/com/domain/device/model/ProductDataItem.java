package www.bwsensing.com.domain.device.model;


import lombok.Data;
import com.alibaba.cola.exception.Assert;
import www.bwsensing.com.domain.common.math.Calculator;

/**
 * 监测项 单位 数据类型
 * 是否考虑监测项与产品数据解析挂钩
 * @author macos-zyj
 */
@Data
public class ProductDataItem {
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
    private DataItemSourceKind itemKind;

    /**
     * 检测项属性类型
     */
    private DataItemAttribute itemAttribute;

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

    public ProductDataItem() {
    }

    public ProductDataItem(String dataId) {
        this.dataId = dataId;
    }

    public ProductDataItem(String itemName, String dataId, String unit,
                           DataItemSourceKind itemKind, Boolean needTransform,
                           String calculationFormula, String placeholder) {
        this.itemName = itemName;
        this.dataId = dataId;
        this.unit = unit;
        this.itemKind = itemKind;
        this.needTransform = needTransform;
        this.calculationFormula = calculationFormula;
        this.placeholder = placeholder;
    }

    public String getDataCalculation(String dataValue){
        if (needTransform){
            Assert.notNull(placeholder, "占位符不能为空!");
            String currentFormula = calculationFormula.replace(placeholder,dataValue);
            return Calculator.conversion(currentFormula)+"";
        }
        return dataValue;
    }
}
