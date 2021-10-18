package www.bwsensing.com.dto.command;

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
public class ProjectSaveCmd extends Command {
    @NotBlank( message ="项目名称不能为空")
    private String name;
    @NotBlank( message ="项目图片不能为空")
    private String picture;
    /**描述*/
    private String comment;
    @Valid
    @NotNull(message = "结构物不能为空")
    @Size(min = 1, message = "至少要包含一种结构物")
    private List<ModelGenerateCmd> structureList;
}
