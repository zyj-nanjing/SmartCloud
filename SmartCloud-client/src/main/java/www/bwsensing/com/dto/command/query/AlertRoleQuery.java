package www.bwsensing.com.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

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
