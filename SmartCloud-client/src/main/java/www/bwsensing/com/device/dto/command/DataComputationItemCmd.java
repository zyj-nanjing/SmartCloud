package www.bwsensing.com.device.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class DataComputationItemCmd extends Command {
    /**
     * 占位前缀
     */
    @NotBlank(message = "占位符不能为空!")
    private String prefix;
    /**
     * 对应的数据相位的类型
     */
    @NotNull(message = "数据类型不能为空!")
    private Integer itemKind;
    /**
     * 选中的编号
     */
    @NotNull(message = "选中编号不能为空!")
    private Integer currentId;
}
