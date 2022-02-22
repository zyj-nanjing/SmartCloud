package www.bwsensing.com.common.scheduler.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.common.scheduler.database.dataobject.BizScheduledConfig;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface BizScheduledMapper {
    /**
     * 查询系统业务定时事务配置(单体多机部署环境)
     *
     * @param id 系统业务定时事务配置(单体多机部署环境)主键
     * @return 系统业务定时事务配置(单体多机部署环境)
     */
    BizScheduledConfig selectBizScheduledConfigById(Integer id);

    /**
     * 根据名称查询定时任务信息及当前权重
     * @param scheduleCode
     * @param serviceId
     * @return
     */
    BizScheduledConfig queryScheduleByCode(@Param("code")String scheduleCode,@Param("serviceId") Integer serviceId);

    /**
     * 查询系统业务定时事务配置(单体多机部署环境)列表
     *
     * @param bizScheduledConfig 系统业务定时事务配置(单体多机部署环境)
     * @return 系统业务定时事务配置(单体多机部署环境)集合
     */
    List<BizScheduledConfig> selectBizScheduledConfigList(BizScheduledConfig bizScheduledConfig);

    /**
     * 新增系统业务定时事务配置(单体多机部署环境)
     *
     * @param bizScheduledConfig 系统业务定时事务配置(单体多机部署环境)
     * @return 结果
     */
    int insertBizScheduledConfig(BizScheduledConfig bizScheduledConfig);

    /**
     * 修改系统业务定时事务配置(单体多机部署环境)
     *
     * @param bizScheduledConfig 系统业务定时事务配置(单体多机部署环境)
     * @return 结果
     */
    int updateBizScheduledConfig(BizScheduledConfig bizScheduledConfig);

    /**
     * 删除系统业务定时事务配置(单体多机部署环境)
     *
     * @param id 系统业务定时事务配置(单体多机部署环境)主键
     * @return 结果
     */
    int deleteBizScheduledConfigById(Integer id);

    /**
     * 批量删除系统业务定时事务配置(单体多机部署环境)
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBizScheduledConfigByIds(String[] ids);

    /**
     * 修改定时任务关联
     * @param weight
     * @param scheduleId
     */
    void updateScheduleRelease(@Param("weight")Double weight,@Param("scheduleId")Integer scheduleId);

    /**
     * 修改定时任务关联中的偏移量
     * @param intervalWeight
     * @param weight
     * @param scheduleId
     */
    void updateScheduleReleaseShift(@Param("intervalWeight")Double intervalWeight,@Param("weight")Double weight,@Param("scheduleId")Integer scheduleId);

    /**
     * 修改对应状态
     * @param serverId
     * @param scheduleId
     * @param isHealthy
     */
    void updateScheduleHealth(@Param("serviceId")Integer serverId, @Param("scheduleId")Integer scheduleId, @Param("isHealthy")Boolean isHealthy);

    /**
     * 获取对应的上一个Schedule权重
     * @param weight
     * @param scheduleId
     * @return
     */
    List<BizScheduledConfig> queryScheduleByWeight(@Param("weight")Double weight,@Param("scheduleId")Integer scheduleId);

    /**
     * 查询事务配置
     * @param serviceId
     * @return
     */
    List<BizScheduledConfig> queryScheduleByServiceId(Integer serviceId);
}
