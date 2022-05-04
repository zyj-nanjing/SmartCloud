package www.bwsensing.com.device.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 产品监测数据项
 * @author macos-zyj
 */
@Data
public class ProductExtraDataItemSaveCmd extends Command {
    /**
     * 产品型号编号
     */
    @NotNull(message = "产品型号编号不能为空!")
    private Integer modelId;
    /**
     * 名称
     */
    @NotBlank(message = "产品型号名称不能为空!")
    private String extraItemName;
    /**
     * 对应数据名称
     */
    @NotBlank(message = "数据编码不能为空!")
    private String extraDataId;

    /**
     * 单位
     */
    private String unit;

    /**
     * 是否需要转换
     */
    @NotNull(message = "请选择是否需要转换")
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
