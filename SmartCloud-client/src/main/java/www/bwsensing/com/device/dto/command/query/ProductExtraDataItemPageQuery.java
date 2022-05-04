package www.bwsensing.com.device.dto.command.query;

import lombok.Data;
import com.alibaba.cola.dto.PageQuery;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProductExtraDataItemPageQuery extends PageQuery {
    @NotNull(message = "产品型号不能为空!")
    private Integer modelId;
}
