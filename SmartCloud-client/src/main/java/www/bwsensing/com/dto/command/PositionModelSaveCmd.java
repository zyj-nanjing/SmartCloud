package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
 * @author macos-zyj
 */
@Data
public class PositionModelSaveCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = 7015841000147151510L;
    /** 位置名称 **/
    @NotBlank( message ="测点名称不能为空!")
    private String name;
    /**点位说明**/
    private String comment;
    /**排序**/
    private Integer orderSort;
}
