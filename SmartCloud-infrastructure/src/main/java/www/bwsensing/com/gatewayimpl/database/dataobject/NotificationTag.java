package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class NotificationTag {
    /**主键*/
    private Integer id;
    /**预警分组编号*/
    private Integer groupId;
    /**告警消息*/
    private String message;
    /**添加时间*/
    private Date addTime;
    /**处理状态 false 未处理 true 以处理*/
    private Boolean status;
    /**处理人编号*/
    private Integer readerId;
    /**查询天数*/
    private Integer queryDay;
}
