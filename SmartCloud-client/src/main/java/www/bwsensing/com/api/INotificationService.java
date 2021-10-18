package www.bwsensing.com.api;

import com.alibaba.cola.dto.PageResponse;
import www.bwsensing.com.dto.clientobject.NotificationCO;
import www.bwsensing.com.dto.command.query.BaseQuery;
import www.bwsensing.com.dto.command.query.NotificationQuery;

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
}
