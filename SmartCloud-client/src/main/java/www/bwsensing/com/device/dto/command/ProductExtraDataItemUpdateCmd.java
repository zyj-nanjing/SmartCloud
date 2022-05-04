package www.bwsensing.com.device.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotNull;

/**
 * 产品监测数据项
 * @author macos-zyj
 */
@Data
public class ProductExtraDataItemUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空!")
    private Integer id;
    /**
     * 产品型号编号
     */
    private Integer modelId;
    /**
     * 名称
     */
    private String extraItemName;
    /**
     * 对应数据名称
     */
    private String extraDataId;

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
