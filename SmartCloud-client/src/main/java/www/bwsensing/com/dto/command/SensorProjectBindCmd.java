package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class SensorProjectBindCmd extends Command {
    private static final long serialVersionUID = 2131212213206403995L;
    /**项目编号*/
    @NotNull(message = "项目编号!")
    private Integer projectId;
    /***/
    @Valid
    @NotNull(message = "传感器列表不能为空")
    @Size(min = 1, message = "列表至少要有一个传感器")
    private List<Integer> sensorIds;
}
