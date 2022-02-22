package www.bwsensing.com.common.mail.database;

import www.bwsensing.com.domain.common.mail.MailConfig;
import www.bwsensing.com.common.mail.database.dataobject.MailConfigDO;

import java.util.List;

/**
 * 邮件服务配置Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-19
 */
public interface MailConfigMapper {
    /**
     * 查询邮件服务配置
     *
     * @param id 邮件服务配置ID
     * @return 邮件服务配置
     */
    MailConfigDO selectConfigById(Integer id);
    /**
     * 查询邮件服务配置
     *
     * @param configName 邮件服务名称
     * @return 邮件服务配置
     */
    MailConfigDO selectConfigByName(String  configName);

    /**
     * 查询邮件服务配置列表
     *
     * @param config 邮件服务配置
     * @return 邮件服务配置集合
     */
    List<MailConfig> selectConfigList(MailConfigDO config);

    /**
     * 新增邮件服务配置
     *
     * @param config 邮件服务配置
     * @return 结果
     */
    int insertConfig(MailConfigDO config);

    /**
     * 修改邮件服务配置
     *
     * @param config 邮件服务配置
     * @return 结果
     */
    int updateConfig(MailConfigDO config);

    /**
     * 删除邮件服务配置
     *
     * @param id 邮件服务配置ID
     * @return 结果
     */
    int deleteConfigById(Integer id);

    /**
     * 批量删除邮件服务配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteConfigByIds(String[] ids);
}
