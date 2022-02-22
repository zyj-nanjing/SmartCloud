package www.bwsensing.com.device.dto.command;

import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class AlertRoleUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull( message = "主键不能为空")
    private Integer id;

    /**
     * 告警名称
     */
    private String alertName;
    /**
     * 回查时间
     */
    private String forward;
    /**
     * 标签
     */
    private String label;
    /**
     * 提示信息
     */
    private String summary;

    /**
     * 预警公式
     */
    private List<String> formulas;

    /**当表达式计算结果为 true 的连续时长超过这个选项时，触发报警，否则报警处于“待定”状态。默认为0，表示一旦计算结果为 true，立即触发报警*/
    private String lastTime;
    /**规则的检查周期，默认1分钟。*/
    private String checkPeriod;
}
