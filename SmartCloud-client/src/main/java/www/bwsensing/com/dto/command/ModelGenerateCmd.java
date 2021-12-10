package www.bwsensing.com.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ModelGenerateCmd extends Command {
    @NotNull(message="模板编号")
    private Integer modelId;
    @NotBlank(message="生成名称")
    private String  generateName;
}
