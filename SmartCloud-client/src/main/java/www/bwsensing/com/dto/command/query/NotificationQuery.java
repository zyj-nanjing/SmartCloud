package www.bwsensing.com.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class NotificationQuery extends PageQuery {
    /**
     * 当前选择的设备
     */
    @NotBlank(message ="选中设备不能为空")
    private String currentDevice;
}
