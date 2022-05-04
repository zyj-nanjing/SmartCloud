package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class ExtraProductDataItemDO {
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
}
