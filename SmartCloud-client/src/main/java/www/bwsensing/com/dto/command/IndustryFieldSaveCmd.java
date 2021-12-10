package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class IndustryFieldSaveCmd extends Command {
    /**名称*/
    @NotBlank(message = "行业名称不能为空!")
    private String name;
}
