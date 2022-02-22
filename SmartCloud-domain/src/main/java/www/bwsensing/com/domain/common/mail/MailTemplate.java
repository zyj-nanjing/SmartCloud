package www.bwsensing.com.domain.common.mail;

import lombok.Data;
import java.util.Date;

/**
 * smtp模板对象 support_smtp_template
 *
 * @author MacOs -Zyj
 * @date 2020-10-08
 */
@Data
public class MailTemplate
{

    /** 主键 */
    private String id;

    /** 邮件标题 */
    private String title;

    /** 模板版本 */
    private String version;

    /** 文件名称 */
    private String fileName;

    /** 模块名称 */
    private String model;

    /** 模板路径 */
    private String templateLocal;

    /** 是否为默认模板默认 */
    private String isDefault;

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
