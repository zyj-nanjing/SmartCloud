package www.bwsensing.com.device.dto.command;

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
public class ProductModelUpdateCmd extends Command {
    private static final long serialVersionUID = 213121913206403995L;
    @NotNull(message="主键不能为空")
    private Integer id;
    /**
     * 型号名称
     * */
    private String productName;

    /**
     * 模型类型
     **/
    private Integer productKindId;

    /**
     * 型号图片
     */
    private String picture;

    /**
     * 说明
     **/
    private String comment;
    /**
     * 直接给到厂家网站路由或商品链接
     */
    private String productDetails;

    /**
     * 交互方式
     */
    private String interactionMode;

    /**
     * 行业领域
     */
    private List<Integer> industryFields;
}
