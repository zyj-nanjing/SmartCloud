package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertGroupDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface AlertGroupMapper {

    /**
     * 条件组合查询
     * @param querySort
     * @return
     */
    List<AlertGroupDO> selectAlertGroupBySort(AlertGroupDO querySort);

    /**
     * 根据用户编号获取所有预警分组
     * @param userId
     * @return
     */
    List<AlertGroupDO> queryAlertGroupByCurrent(Integer userId);
    /**
     * ID 获取
     * @param id
     * @return
     */
    AlertGroupDO getAlertGroupById(Integer id);

    /**
     * 获取当前分组需要推送的邮箱集合
     * @param groupId
     * @return
     */
    List<String> getGroupNotificationMembers(Integer groupId);

    /**
     * 数据插入
     * @param saveAlertGroup
     */
    void saveAlertGroup(AlertGroupDO saveAlertGroup);

    /**
     * 数据修改
     * @param saveAlertGroup
     */
    void updateAlertGroup(AlertGroupDO saveAlertGroup);

    /**
     * 根据Id 删除
     * @param id
     */
    void  deleteAlertGroupById(Integer id);
}
