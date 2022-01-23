package www.bwsensing.com.dto.command;

import lombok.Data;
import java.util.List;
import javax.validation.Valid;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author macos-zyj
 */
@Data
public class StructureTemplateUpdateCmd extends Command {
    @ApiModelProperty(name ="主键ID",required = true)
    @NotNull(message="主键ID不能为空!")
    private Integer id;
    /** 模板名称 */
    @ApiModelProperty(name ="组织模板名称",required = true)
    @NotBlank(message="组织结构模板不能为空!")
    private String templateName;
    /**所属行业*/
    @Valid
    @NotNull(message = "数据列表不能为空")
    @Size(min = 1, message = "列表至少包含一个行业")
    private List<Integer> belowFields;
}
