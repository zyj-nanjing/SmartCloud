package www.bwsensing.com.device.dto.command.query;

import lombok.Data;
import com.alibaba.cola.dto.PageQuery;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class DataComputationModelPageQuery extends PageQuery {

    @NotNull(message = "产品编号不能为空!")
    private Integer modelId;
}
