package www.bwsensing.com.device.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author macos-zyj
 */
@Data
public class SensorModelSaveCmd  extends Command {
    /**
     *
     */
    private static final long serialVersionUID = 213121229206403995L;
    /**模型名称*/
    @NotBlank(message = "模型名称不能为空")
    private String modelName;
    /**模型类型**/
    @NotBlank(message = "模型类型不能为空")
    private String modelKind;
    /**型号图片*/
    @NotBlank(message = "型号图片不能为空")
    private String picture;
    /**说明**/
    private String comment;
    /**监控类型*/
    @Valid
    @NotNull(message = "监控类型不能为空")
    @Size(min = 1, message = "监控类型列表至少要有一个监测因素")
    private List<Integer> prototypeList;
}
