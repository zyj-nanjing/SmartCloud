package www.bwsensing.com.device.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

/**
 * 产品监测数据项
 * @author macos-zyj
 */
@Data
public class ProductDataItemSaveCmd  extends Command {
    /**
     * 产品型号编号
     */
    @NotNull(message = "产品型号编号不能为空!")
    private Integer modelId;
    /**
     * 名称
     */
    @NotBlank(message = "产品型号名称不能为空!")
    private String itemName;
    /**
     * 对应数据名称
     */
    @NotBlank(message = "数据编码不能为空!")
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
    @NotNull(message = "数据项来源类型不能为空!")
    private Integer itemKind;

    /**
     * 检测项属性类型
     */
    @NotNull(message = "检测项属性类型不能为空!")
    private Integer itemAttribute;

    /**
     * 是否需要转义
     */
    @NotNull(message = "请确认是否需要转义!")
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
