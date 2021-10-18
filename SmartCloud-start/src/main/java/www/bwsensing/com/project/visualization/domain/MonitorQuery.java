package www.bwsensing.com.project.visualization.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class MonitorQuery {
    /**
     * 当前选择的设备
     */
    @NotBlank(message ="选中设备不能为空")
    private String currentDevice;

    /**
     * 单一参数查询条件
     */
    private String code;

    /**
     * 数据查询类型
     */
    @NotBlank(message ="查询类型不能为空")
    private String datatype;

    /**
     * 类型
     */
    @NotNull(message ="查询参数集合")
    private List<String> paramIds;
    /**
     *全量数据不需要时间间隔
     * 时间间隔
     */
    private String interval;

    /**
     * 开始时间
     */
    @NotBlank(message ="起始时间不能为空")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(message ="结束时间不能为空")
    private String endTime;
}
