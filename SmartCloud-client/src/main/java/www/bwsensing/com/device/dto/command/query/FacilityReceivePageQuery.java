package www.bwsensing.com.device.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class FacilityReceivePageQuery extends PageQuery {
    @NotBlank(message = "传感器地址不能为空")
    private String sn;
}
