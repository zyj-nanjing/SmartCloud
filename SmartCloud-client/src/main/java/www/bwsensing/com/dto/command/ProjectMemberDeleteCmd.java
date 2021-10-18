package www.bwsensing.com.dto.command;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProjectMemberDeleteCmd {
    /**项目编号*/
    @NotNull(message = "项目编号不能为空")
    private Integer projectId;
    /**成员编号*/
    @NotNull(message = "成员编号不能为空")
    private Integer memberId;
}
