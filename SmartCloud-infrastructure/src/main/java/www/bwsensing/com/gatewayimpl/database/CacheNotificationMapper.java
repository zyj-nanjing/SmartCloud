package www.bwsensing.com.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.NotificationTag;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface CacheNotificationMapper {

    /**
     * 更新状态
     * @param readerId
     * @param id
     */
    void updateCacheStatus(@Param("readerId")Integer readerId,@Param("readerId")Integer id);

    /**
     * 保存
     * @param notificationTag 通知标签
     */
    void  insertNotificationTag(NotificationTag notificationTag);

    /**
     * 查询通知栏信息
     * @param queryData
     * @return
     */
    List<NotificationTag> queryNotification(NotificationTag queryData);
}
