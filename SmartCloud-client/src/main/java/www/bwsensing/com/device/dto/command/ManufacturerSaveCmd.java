package www.bwsensing.com.device.dto.command;

import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * @author macos-zyj
 */
@Data
public class ManufacturerSaveCmd {
    /**名称*/
    @NotBlank(message = "厂商名称不能为空")
    private String name;
    /**邮编*/
    @NotBlank(message = "邮政编码不能为空")
    private String postCode;
    /**地址*/
    @NotBlank(message = "厂商地址不能为空")
    private String address;
    /**联系者*/
    @NotBlank(message = "联系人不能为空")
    private String connector;
    /**联系电话*/
    @NotBlank(message = "联系电话不能为空")
    private String contactNumber;
    /**是否为北微厂*/
    private Boolean isInner;
}
