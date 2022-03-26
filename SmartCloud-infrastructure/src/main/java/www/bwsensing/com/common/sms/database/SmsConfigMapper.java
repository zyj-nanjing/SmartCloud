package www.bwsensing.com.common.sms.database;

import org.apache.ibatis.annotations.Mapper;
import www.bwsensing.com.common.sms.database.dataobject.SmsConfigDO;
import java.util.List;

/**
 * @author mac_zyj
 */
@Mapper
public interface SmsConfigMapper {
    /**
     * 查询短信配置
     *
     * @param id 短信配置ID
     * @return 短信配置
     */
    SmsConfigDO selectSmsConfigById(Long id);

    /**
     * 根据配置名称查询配置
     * @param configName 配置名称
     * @return 配置
     */
    SmsConfigDO selectSmsConfigByConfigName(String configName);
    /**
     * 查询短信配置列表
     *
     * @param smsConfig 短信配置
     * @return 短信配置集合
     */
    List<SmsConfigDO> selectSmsConfigList(SmsConfigDO smsConfig);

    /**
     * 新增短信配置
     *
     * @param smsConfig 短信配置
     * @return 结果
     */
    int insertSmsConfig(SmsConfigDO smsConfig);

    /**
     * 修改短信配置
     *
     * @param smsConfig 短信配置
     * @return 结果
     */
    int updateSmsConfig(SmsConfigDO smsConfig);

    /**
     * 删除短信配置
     *
     * @param id 短信配置ID
     * @return 结果
     */
    int deleteSmsConfigById(Long id);

    /**
     * 批量删除短信配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSmsConfigByIds(String[] ids);
}
