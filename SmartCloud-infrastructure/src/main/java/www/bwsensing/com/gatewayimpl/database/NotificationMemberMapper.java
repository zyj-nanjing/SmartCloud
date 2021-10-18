package www.bwsensing.com.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.NotificationMemberDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface NotificationMemberMapper {
    /**
     * 获取消息推送人员
     * @param groupId
     * @return
     */
    List<NotificationMemberDO> getNotificationMemberByGroupId(Integer groupId);

    /**
     * 删除
     * @param groupId
     */

    void deleteNotificationMemberByGroupId(Integer groupId);

    /**
     * 根据用户Id进行删除
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * 删除
     * @param groupId
     * @param userId
     */
    void  saveNotificationMember(@Param("groupId")Integer groupId, @Param("userId")Integer userId);
}
