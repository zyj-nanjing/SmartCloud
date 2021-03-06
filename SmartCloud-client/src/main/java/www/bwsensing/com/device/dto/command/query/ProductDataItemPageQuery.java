package www.bwsensing.com.device.dto.command.query;

import lombok.Data;
import com.alibaba.cola.dto.PageQuery;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProductDataItemPageQuery extends PageQuery {
    @NotNull(message = "产品型号不能为空!")
    private Integer modelId;

    /**
     * 数据项来源类型
     */
    private Integer itemKind;

}
