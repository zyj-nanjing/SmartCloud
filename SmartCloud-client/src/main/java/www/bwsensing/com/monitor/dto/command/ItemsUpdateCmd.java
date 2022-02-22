package www.bwsensing.com.monitor.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;
/**
 * @author macos-zyj
 */
@Data
public class ItemsUpdateCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = -4626336790922118388L;
    private Integer id;
    /**名称**/
    private String itemName;
    /**对应数据名称**/
    private String parameterName;
    /**数据类型**/
    private String itemNumberType;
    /**单位符**/
    private String itemSymbol;
    /**顺序**/
    private Integer orderSort;
    /**是否有效**/
    private boolean isEffective;
}
