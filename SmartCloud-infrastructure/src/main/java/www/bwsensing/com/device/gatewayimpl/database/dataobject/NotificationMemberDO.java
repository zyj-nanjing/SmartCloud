package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class NotificationMemberDO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 预警分组编号
     */
    private Integer groupId;
    /**
     * 预警接收人员编号
     */
    private Integer userId;
}
