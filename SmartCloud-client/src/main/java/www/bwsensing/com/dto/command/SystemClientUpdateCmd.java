package www.bwsensing.com.dto.command;

import lombok.Data;
import java.util.List;
import javax.validation.Valid;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author macos-zyj
 */
@Data
public class SystemClientUpdateCmd extends Command {
    /**主键Id*/
    @NotNull(message = "主键不能为空!")
    private Integer id;
    /**客户名称*/
    @NotBlank(message = "客户名称不能为空!")
    private String clientName;
    /**客户简称*/
    @NotBlank(message = "客户简称不能为空!")
    private String clientShortName;
    /**客户品牌标识*/
    @NotBlank(message = "客户品牌标识不能为空!")
    private String clientLogo;
    /** 国家地区 */
    private String country;
    /**客户电话*/
    private String clientPhone;
    /**客户传真*/
    private String clientFax;
    /**客户地址*/
    private String clientAddress;
    /**客户邮箱*/
    private String email;
    /** 客户来源 */
    @NotBlank(message = "客户来源不能为空!")
    private String comesFrom;
    /** 等级 */
    private String level;
    /** 客户类型 */
    @NotBlank(message = "客户类型不能为空!")
    private String clientType;
    /** 业务员 */
    private String salesman;
    /**客户状态*/
    private String clientStatus;
    /**客户描述*/
    private String clientDesc;
    /**相关行业领域*/
    @Valid
    @NotNull(message = "数据列表不能为空")
    @Size(min = 1, message = "列表至少包含一个行业")
    private List<Integer> releaseFields;
}
