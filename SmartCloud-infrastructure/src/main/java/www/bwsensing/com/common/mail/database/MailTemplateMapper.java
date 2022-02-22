package www.bwsensing.com.common.mail.database;

import www.bwsensing.com.common.mail.database.dataobject.MailTemplateDO;

/**
 * smtp模板DAO接口
 *
 * @author ruoyi
 * @date 2020-10-08
 */
public interface MailTemplateMapper {
    /**
     * 根据模板名称获取模板信息
     * @param fileName 模板文件名称
     * @return 模板信息
     */
    MailTemplateDO selectTemplateByName(String fileName);
}
