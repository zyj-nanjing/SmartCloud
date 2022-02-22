package www.bwsensing.com.monitor.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;
/**
 * @author macos-zyj
 */
@Data
public class PositionModelUpdateCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = 7754907456081868674L;
    private Integer id;
    /**位置名称**/
    private String name;
    /**点位说明**/
    private String comment;
}
