package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class MailConfigDO {
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
