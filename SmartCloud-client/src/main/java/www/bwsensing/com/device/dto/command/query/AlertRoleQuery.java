package www.bwsensing.com.device.dto.command.query;

import lombok.Data;
import com.alibaba.cola.dto.PageQuery;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class AlertRoleQuery extends PageQuery {
    /**
     * 预警分组编号Id
     */
    @NotNull(message="预警分组编号不能为空")
    private Integer alertGroupId;
}
