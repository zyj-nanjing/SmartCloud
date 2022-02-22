package www.bwsensing.com.common.mail.database.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class MailTemplateDO {
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
