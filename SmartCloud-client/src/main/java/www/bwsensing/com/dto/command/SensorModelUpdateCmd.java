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
public class SensorModelUpdateCmd extends Command {
    private static final long serialVersionUID = 213121913206403995L;
    @NotNull(message="主键不能为空")
    private Integer id;
    /**模型名称*/
    private String modelName;
    /**模型类型**/
    private String modelKind;
    /**型号图片*/
    private String picture;
    /**说明**/
    private String comment;
    /**监控类型*/
    @Valid
    @NotNull(message = "监控类型不能为空")
    @Size(min = 1, message = "监控类型列表至少要有一个测点")
    private List<Integer> prototypeList;
}
