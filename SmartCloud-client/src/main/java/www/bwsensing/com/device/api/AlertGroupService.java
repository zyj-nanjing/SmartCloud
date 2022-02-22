package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.device.dto.command.query.AlertGroupQuery;
import www.bwsensing.com.device.dto.command.AlertGroupSaveCmd;
import www.bwsensing.com.device.dto.command.AlertGroupUpdateCmd;
import www.bwsensing.com.device.dto.command.NotificationMemberCmd;
import www.bwsensing.com.device.dto.clientobject.AlertGroupCO;
import www.bwsensing.com.device.dto.clientobject.NotificationMemberBindCO;

/**
 * @author macos-zyj
 */
public interface AlertGroupService {
    /**
     * 保存
     * @param saveCmd
     * @return
     */
    Response saveAlertGroup(AlertGroupSaveCmd saveCmd);

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updateAlertGroup(AlertGroupUpdateCmd updateCmd);
    /**
     * 删除
     * @param id
     * @return
     */
    Response deleteAlertGroup(Integer id);

    /**
     * 保存通知人员
     * @param saveCmd
     * @return
     */
    Response saveNotificationMember(NotificationMemberCmd saveCmd);

    /**
     * 分页查询
     * @param groupQuery
     * @return
     */
    PageResponse<AlertGroupCO> alertGroupPageQuery(AlertGroupQuery groupQuery);

    /**
     * 获取通知人员
     * @param groupId
     * @return
     */
    MultiResponse<NotificationMemberBindCO> queryNotificationMember(Integer groupId);
}
