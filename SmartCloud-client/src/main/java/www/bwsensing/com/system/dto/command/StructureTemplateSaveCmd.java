package www.bwsensing.com.system.dto.command;


import lombok.Data;
import java.util.List;
import javax.validation.Valid;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author macos-zyj
 */
@Data
public class StructureTemplateSaveCmd extends Command {
    /** 模板名称 */
    @NotBlank(message="组织结构模板不能为空!")
    private String templateName;

    /**所属行业*/
    @Valid
    @NotNull(message = "数据列表不能为空")
    @Size(min = 1, message = "列表至少包含一个行业")
    private List<Integer> belowFields;
}
