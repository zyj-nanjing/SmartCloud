package www.bwsensing.com.device.dto.command;

import lombok.Data;
import java.util.List;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class DataComputationModelUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空!")
    private Integer id;

    /**
     * 计算模型名称
     */
    private String name;

    /**
     * 额外数据
     */
    private List<DataComputationItemCmd> dataComputationItems;

    /**
     * 计算公式描述
     */
    private String formulaName;

    /**
     * 计算公式
     */
    private String computationFormula;
}
