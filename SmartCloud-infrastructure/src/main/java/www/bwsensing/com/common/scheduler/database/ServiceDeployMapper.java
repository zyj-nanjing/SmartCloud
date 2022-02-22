package www.bwsensing.com.common.scheduler.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.common.scheduler.database.dataobject.ServiceDeploy;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ServiceDeployMapper {
    /**
     * 查询系统部署信息
     *
     * @param id 系统部署信息主键
     * @return 系统部署信息
     */
    ServiceDeploy selectServiceDeployById(Integer id);

    /**
     * 查询系统部署信息列表
     *
     * @param serviceDeploy 系统部署信息
     * @return 系统部署信息集合
     */
    List<ServiceDeploy> selectServiceDeployList(ServiceDeploy serviceDeploy);

    /**
     * 根据权级获取对应的系统信息
     * @param weight
     * @param scheduleId
     * @return
     */
    List<ServiceDeploy> selectServiceDeployByWeight(@Param("weight")Double weight, @Param("scheduleId")Integer scheduleId);

    /**
     * 新增系统部署信息
     *
     * @param serviceDeploy 系统部署信息
     * @return 结果
     */
    int insertServiceDeploy(ServiceDeploy serviceDeploy);

    /**
     * 修改系统部署信息
     *
     * @param serviceDeploy 系统部署信息
     * @return 结果
     */
    int updateServiceDeploy(ServiceDeploy serviceDeploy);

    /**
     * 删除系统部署信息
     *
     * @param id 系统部署信息主键
     * @return 结果
     */
    int deleteServiceDeployById(Integer id);

    /**
     * 批量删除系统部署信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteServiceDeployByIds(String[] ids);
}
