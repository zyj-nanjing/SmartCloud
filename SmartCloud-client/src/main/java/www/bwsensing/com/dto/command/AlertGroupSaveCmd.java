package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class AlertGroupSaveCmd extends Command {
    /**
     * 分组名称
     */
    @NotBlank(message="分组名称不能为空")
    private String groupName;
    /**
     * 是否包含模板
     */
    @NotNull(message="是否包含模板不能为空")
    private Boolean isTemplate;

    /**
     * 推送方式
     */
    @NotNull(message="推送方式不能为空!")
    private Integer pushMethod;
    /**
     * 模板编号
     */
    private Integer templateId;

    @NotNull(message="传感器编号不能为空")
    private Integer sensorId;

    /**
     * 规则集合
     */
    private List<AlertRoleAddCmd> roleCollection;
}
