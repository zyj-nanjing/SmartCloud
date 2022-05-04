package www.bwsensing.com.device.dto.command;
import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotNull;

/**
 * 产品监测数据项
 * @author macos-zyj
 */
@Data
public class ProductDataItemUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空!")
    private Integer id;

    /**
     * 名称
     */
    private String itemName;

    /**
     * 单位
     */
    private String unit;

    /**
     * 小数点后长
     */
    private Integer decimalSize;

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
