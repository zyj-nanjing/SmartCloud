package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class ProductDataItemDO {
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
     * 数据编码
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
     * 数据项来源类型
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
}
