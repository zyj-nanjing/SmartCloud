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
public class StructureModelSaveCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = 2211111254414395007L;
    /** 名称 */
    @NotBlank(message = "结构物名称不能为空")
    private String name;
    @NotBlank(message = "结构物图片不能为空")
    private String picture;
    /**描述*/
    private String comment;
    /**是否为公有结构物*/
    private Boolean isPublic;
    /**是否包含手机号*/
    private Boolean isContainMobile;
    /**是否包含经纬度*/
    private Boolean isContainPosition;
    /**排序**/
    private Integer orderSort;
    /**传感器模板标位*/
    @Valid
    @NotNull(message = "测点列表不能为空")
    @Size(min = 1, message = "测点列表至少要有一个测点")
    private List<PositionModelSaveCmd> positionModels;
}
