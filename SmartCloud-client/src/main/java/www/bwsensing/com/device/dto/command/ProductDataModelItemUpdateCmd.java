package www.bwsensing.com.device.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProductDataModelItemUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空!")
    private Integer id;


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
     * 计算公式
     */
    private String calculationFormula;

    /**
     * 占位符
     */
    private String placeholder;
}
