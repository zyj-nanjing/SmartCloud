package www.bwsensing.com.device.dto.command.query;

import lombok.Data;
import com.alibaba.cola.dto.PageQuery;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class DeviceComputationPageQuery extends PageQuery {
    /**
     * 设备编号
     */
    @NotNull(message = "设备编号不能为空!")
    private Integer deviceId;
}
