package www.bwsensing.com.device.dto.command;

import com.alibaba.cola.dto.Command;
import java.util.List;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author macos-zyj
 */
@Data
public class NotificationMemberCmd extends Command {
    /**
     * 当前分组
     */
    @NotNull(message = "当前分组编号不能为空")
    private Integer currentGroup;
    /**
     * 数据
     */
    @Valid
    @NotNull(message = "数据列表不能为空")
    @Size(min = 1, message = "列表至少要有一个成员")
    private List<Integer> dataList;
}
