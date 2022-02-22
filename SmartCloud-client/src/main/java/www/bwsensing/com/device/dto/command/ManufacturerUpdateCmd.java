package www.bwsensing.com.device.dto.command;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ManufacturerUpdateCmd {
    /**主键*/
    @NotNull( message = "主键不能为空!")
    private Integer id;
    /**名称*/
    private String name;
    /**邮编*/
    private String postCode;
    /**地址*/
    private String address;
    /**联系者*/
    private String connector;
    /**联系电话*/
    private String contactNumber;
    /**是否为北微厂*/
    private Boolean isInner;
}
