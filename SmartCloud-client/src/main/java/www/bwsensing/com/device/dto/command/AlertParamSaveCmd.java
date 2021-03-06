package www.bwsensing.com.device.dto.command;

import lombok.Data;
import java.util.List;
import com.alibaba.cola.dto.Command;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * @author macos-zyj
 */
@Data
public class AlertParamSaveCmd extends Command {
    /**告警名称**/
    @NotBlank(message = "告警名称不能为空")
    private String alertName;
    /**监测参数编号 注数据库编号*/
    @NotNull(message = "监测参数编号不能为空")
    private Integer paramNo;
    /**问题发生后的持续时间  默认为 0 */
    private String lastTime;
    /**监测周期 默认为1m*/
    private String period;
    /**
     * 每组格式为 (max,>,10,&&)  第一位为 函数 可以为 max avg min  第二位为 运算符 可以为 < <= >  >= != == 第三位为具体数据 第四位为 逻辑或按位 运算符  || | && & 注意数组末尾不要加加了校验不会让你过的
     **/
    @Valid
    @NotNull(message = "监测公式不能为空")
    @Size(min = 1, message = "监测公式至少要有一个自定义属性")
    private List<String> formulas;
    /**颜色*/
    private String color;
}
