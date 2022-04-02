package www.bwsensing.com.device.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertNotificationDO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface AlertNotificationMapper {

    /**
     * 条件查询
     * @param querySort
     * @return
     */
    List<AlertNotificationDO> selectNotificationBySort(AlertNotificationDO querySort);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    AlertNotificationDO getNotificationById(Integer id);

    /**
     * 按照时间查询记录
     * @param roleName
     * @param time
     * @return
     */
    Integer countNotificationByRoleNameAndTime(@Param("roleName")String roleName,@Param("alertTime") Date time);

    /**
     * 保存
     * @param notification
     */
    void saveNotification(AlertNotificationDO notification);

    /**
     * 根据ID进行删除
     * @param id
     */
    void deleteNotificationById(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteNotificationByIds(String ids);
}
