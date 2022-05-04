package www.bwsensing.com.device.dto.command;

import lombok.Data;
import java.util.List;
import javax.validation.Valid;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author macos-zyj
 */
@Data
public class ProductModelSaveCmd extends Command {
    /**
     *
     */
    private static final long serialVersionUID = 213121229206403995L;
    /**
     * 型号名称
     * */
    @NotBlank(message = "型号名称不能为空")
    private String productName;

    /**
     * 模型类型
     **/
    @NotNull(message = "模型类型不能为空")
    private Integer productKindId;

    /**
     * 型号图片
     */
    @NotBlank(message = "型号图片不能为空")
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
