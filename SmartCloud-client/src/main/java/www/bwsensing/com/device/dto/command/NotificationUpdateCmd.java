package www.bwsensing.com.device.dto.command;

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
public class NotificationUpdateCmd  extends Command {
    private static final long serialVersionUID = 1292710121311732257L;
    @Valid
    @NotNull(message = "处理列表不能为空")
    @Size(min = 1, message = "列表至少要有一个处理消息")
    private List<Integer> updateIds;
}
