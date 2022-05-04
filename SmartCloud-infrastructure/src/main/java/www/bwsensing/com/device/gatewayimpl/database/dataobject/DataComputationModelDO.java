package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.List;

/**
 * 计算模型
 * @author macos-zyj
 */
@Data
public class DataComputationModelDO {
    /**
     * 主键
     */
    private Integer id;

    /**名称*/
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
     * 监测数据项
     */
    private List<ProductDataItemDO> productDataItems;

    /**
     * 额外数据
     */
    private List<ExtraProductDataItemDO> extraProductDataItems;

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
}
