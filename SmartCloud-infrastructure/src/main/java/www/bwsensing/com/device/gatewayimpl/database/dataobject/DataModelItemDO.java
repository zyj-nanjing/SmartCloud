package www.bwsensing.com.device.gatewayimpl.database.dataobject;


import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class DataModelItemDO {
    /**
     * 主键
     */
    private Integer id;

    private Integer modelId;

    private Integer dataItemId;

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
     * 监测因素
     */
    private ProductDataItemDO protoItem;

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
}
