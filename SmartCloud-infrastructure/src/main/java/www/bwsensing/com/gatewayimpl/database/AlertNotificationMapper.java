package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.AlertNotificationDO;
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
