package www.bwsensing.com.monitor.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
/**
 * @author macos-zyj
 */
@Data
public class StructureModelUpdateCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = -2452724942610214452L;
    /** 主键 */
    @NotNull
    private int id;
    /**名称*/
    @NotBlank(message = "名称不能为空!")
    private String name;
    /**是否包含手机号*/
    private Boolean isContainMobile;
    /**是否包含经纬度*/
    private Boolean isContainPosition;
    /**描述*/
    private String comment;
    /**排序**/
    private Integer orderSort;
    /**传感器模板标位*/
    @Valid
    @NotNull(message = "测点列表不能为空")
    @Size(min = 1, message = "测点列表至少要有一个测点")
    private List<PositionModelUpdateCmd> positionList;
}
