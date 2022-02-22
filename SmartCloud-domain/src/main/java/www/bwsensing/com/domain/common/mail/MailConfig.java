package www.bwsensing.com.domain.common.mail;

import lombok.Data;
import java.util.Date;

/**
 * 邮件服务配置对象 smtp_config
 * @author MacOs -Zyj
 * @date 2020-10-19
 */
@Data
public class MailConfig {
    /** 主键 */
    private Integer id;

    /** 配置名称 */
    private String configName;

    /** 账户名称 */
    private String accountName;

    /** 发送人 */
    private String fromAlias;

    /** 发送地址 */
    private String replyToAddress;

    /** 标签 */
    private String tagName;

    /** 模板名称 */
    private String templateName;

    /** 订阅 */
    private String subscribe;

    /** 0 简答发送 1 批量发送 */
    private String type;

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
