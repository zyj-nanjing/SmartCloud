package www.bwsensing.com.monitor.dto.command;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class IndustryFieldUpdateCmd {
    /**主键*/
    @NotNull(message = "主键不能为空!")
    private Integer id;
    /**行业领域名称*/
    @NotBlank(message = "行业名称能为空!")
    private String name;
}
