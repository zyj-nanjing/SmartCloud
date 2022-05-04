package www.bwsensing.com.device.dto.command.query;


import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProductDataModelItemsPageQuery extends PageQuery {
    /**
     * 数据模型编号
     */
    @NotNull(message = "数据模型编号不能为空!")
    private Integer modelId;

    /**
     * JSON key
     */
    private String keyName;

    /**
     * 编码类型
     */
    private String itemKind;


    /***
     * 唯一编码长度
     */
    private Integer uniqueCodeSize;

    /**
     * 监测因素编号
     */
    private Integer dataItemId;

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
     * 占位符
     */
    private String placeholder;
}
