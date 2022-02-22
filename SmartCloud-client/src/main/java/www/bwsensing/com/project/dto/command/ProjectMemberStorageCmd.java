package www.bwsensing.com.project.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProjectMemberStorageCmd extends Command {
    /**项目编号*/
    @NotNull(message="项目编号不能为空!")
    private Integer projectId;
    /**人员编号*/
    @NotNull(message="人员编号不能为空!")
    private Integer memberId;
    @NotBlank(message = "角色Code不能为空")
    private String roleCode;
}
