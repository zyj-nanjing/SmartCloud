package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;
/**
 * @author macos-zyj
 */
@Data
public class PrototypeUpdateCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = -7472574597251919306L;
    @NotNull(message = "主键编号不能为空")
    private Integer id;
    private String  typeName;
    private String itemsJson;
    private Integer orderSort;
    private List<ItemsUpdateCmd> itemsUpdateList;
}
