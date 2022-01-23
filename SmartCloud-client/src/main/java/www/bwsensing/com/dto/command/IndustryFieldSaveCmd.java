package www.bwsensing.com.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class IndustryFieldSaveCmd extends Command {
    /**名称*/
    @NotBlank(message = "行业名称不能为空!")
    private String name;
    /**编码*/
    @NotBlank(message = "行业编码不能为空!")
    private String code;
}
