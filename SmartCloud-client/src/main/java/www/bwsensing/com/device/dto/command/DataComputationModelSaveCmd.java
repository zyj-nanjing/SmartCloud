package www.bwsensing.com.device.dto.command;

import lombok.Data;
import java.util.List;
import javax.validation.Valid;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class DataComputationModelSaveCmd extends Command {

    /**
     * 计算模型名称
     */
    @NotBlank(message = "模型名称不能为空!")
    private String name;

    /**
     * 产品型号Id
     */
    @NotNull(message = "产品型号编号不能为空!")
    private Integer modelId;

    /**
     * 数据项Id
     */
    @NotNull(message = "数据项编号不能为空!")
    private Integer dataItemId;

    /**
     * 计算模型关联数据项
     */
    @Valid
    @NotNull(message = "计算模型关联数据项列表不能为空")
    @Size(min = 1, message = "列表至少要有一个计算模型关联数据项")
    private List<DataComputationItemCmd> dataComputationItems;

    /**
     * 计算公式名称
     */
    @NotBlank(message = "公式描述不能为空!")
    private String formulaName;
    /**
     * 计算公式
     */
    @NotBlank(message = "计算公式不能为空!")
    private String computationFormula;
}
