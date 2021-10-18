package www.bwsensing.com.dto.command;

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
public class PrototypeAddCmd extends Command {
    @NotBlank(message = "监测类型名称不能为空!")
    private String  typeName;
    private Integer orderSort;
    @Valid
    @NotNull(message = "检测项类型不能为空")
    @Size(min = 1, message = "检测项类型至少要有一个自定义属性")
    private List<ItemsSaveCmd> itemsSaveList;
}
