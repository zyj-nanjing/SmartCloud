package www.bwsensing.com.domain.device.model;

import lombok.Data;
import com.alibaba.cola.exception.Assert;
import org.apache.commons.lang3.StringUtils;
import www.bwsensing.com.domain.common.math.Calculator;

import java.text.DecimalFormat;

/**
 * @author macos-zyj
 */
@Data
public class ExtraProductDataItem {
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

    public ExtraProductDataItem() {

    }

    public ExtraProductDataItem(Integer id, String extraData) {
        this.id = id;
        this.extraData = extraData;
    }

    public ExtraProductDataItem(String extraItemName,
                                String extraDataId, String extraData, String unit,
                                Boolean needTransform, String calculationFormula, String placeholder) {
        this.extraItemName = extraItemName;
        this.extraDataId = extraDataId;
        this.extraData = extraData;
        this.unit = unit;
        this.needTransform = needTransform;
        this.calculationFormula = calculationFormula;
        this.placeholder = placeholder;
    }

    public String getDataCalculation(){
        DecimalFormat df = new DecimalFormat("0.000000");
        if (needTransform&& StringUtils.isNotBlank(extraData)){
            Assert.notNull(placeholder, "占位符不能为空!");
            String currentFormula = calculationFormula.replace(placeholder,extraData);
            return df.format(Calculator.conversion(currentFormula));
        }
        return extraData;
    }

}
