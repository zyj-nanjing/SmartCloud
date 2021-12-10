package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class OperateGroupUpdateCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = -6066299360080599999L;
    @NotNull(message = "组员编号不能为空!")
    private Integer id;
    /**组名*/
    private String groupName;
    /**是否为内部部门*/
    private Boolean isInner;
    /**公司名称*/
    private String companyName;
}
