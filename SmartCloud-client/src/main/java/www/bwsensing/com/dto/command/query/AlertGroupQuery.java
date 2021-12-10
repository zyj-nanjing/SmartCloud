package www.bwsensing.com.dto.command.query;

import lombok.Data;
import com.alibaba.cola.dto.PageQuery;
import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class AlertGroupQuery extends PageQuery {
    /**
     * 当前选择的设备
     */
    @NotBlank(message ="选中设备不能为空")
    private String currentDevice;
}
