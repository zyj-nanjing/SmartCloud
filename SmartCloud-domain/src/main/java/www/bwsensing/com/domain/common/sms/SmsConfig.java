package www.bwsensing.com.domain.common.sms;

import lombok.Data;
import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class SmsConfig {
    /** 主键 */
    private Long id;

    /** 配置名称 */
    private String configName;

    /** 短信签名 */
    private String signName;

    /** 模板名称 */
    private String templateCode;

    /**是否开启*/
    private Boolean isOpen;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;
}
