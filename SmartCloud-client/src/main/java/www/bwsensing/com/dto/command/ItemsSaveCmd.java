package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
 * @author macos-zyj
 */
@Data
public class ItemsSaveCmd extends Command {
    /**名称**/
    @NotBlank(message = "检测项名称不能为空!")
    private String itemName;
    /**对应数据名称**/
    @NotBlank(message="参数名称不能为空!")
    private String parameterName;
    /**数据类型**/
    @NotBlank(message ="数据类型不能为空!")
    private String itemNumberType;
    /**单位符**/
    @NotBlank(message ="单位符不能为空!")
    private String itemSymbol;
    /**顺序**/
    private Integer orderSort;
}
