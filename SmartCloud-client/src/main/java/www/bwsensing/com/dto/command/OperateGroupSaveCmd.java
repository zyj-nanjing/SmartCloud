package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
 * @author macos-zyj
 */
@Data
public class OperateGroupSaveCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = 1792714004361732257L;
    /** 组名 */
    @NotBlank(message="组名不能为空")
    private String groupName;
    /**组号*/
    @NotBlank(message="组编码不能为空")
    private String groupCode;
    /**是否为内部部门*/
    private Boolean isInner;
    /**公司名称*/
    private String companyName;
}
