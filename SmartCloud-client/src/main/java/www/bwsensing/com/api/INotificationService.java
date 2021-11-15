package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.dto.clientobject.NotificationCO;
import www.bwsensing.com.dto.clientobject.NotificationMsgCO;
import www.bwsensing.com.dto.command.query.NotificationQuery;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface INotificationService {
    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    PageResponse<NotificationCO> selectNotificationByPage(NotificationQuery pageQuery);

    /**
     * 缓存通知消息
     * @param groupId
     * @param message
     */
    void cacheNotification(Integer groupId, String message);

    /**
     * 获取当前通知消息
     * @return
     */
    MultiResponse<NotificationMsgCO> getCurrentMessage();

    /**
     * 修改已读状态
     * @param updateIds
     * @return
     */
    Response updateCacheStatus(List<Integer> updateIds);
}
