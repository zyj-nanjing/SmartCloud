package www.bwsensing.com.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.DTO;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class AlertGroupUpdateCmd extends DTO {
    /** 主键 */
    @NotNull(message="主键不能为空!")
    private Integer id;

    /** 预警分组名称 */
    @NotBlank(message="分组名称不能为空")
    private String groupName;
}
