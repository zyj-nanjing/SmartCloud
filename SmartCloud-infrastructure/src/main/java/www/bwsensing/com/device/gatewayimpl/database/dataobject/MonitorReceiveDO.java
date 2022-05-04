package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;

/**
 * 数据接收日志
 * @author macos-zyj
 */
@Data
public class MonitorReceiveDO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * Sn编码
     */
    private String uniqueCode;
    /**
     * 频道编号
     */
    private String channelId;
    /**
     * Ip地址
     */
    private String ip;

    /**
     * 接收时间
     */
    private Date receiveTime;

    /**
     * 接收数量
     */
    private Integer receiveSize;
    /**
     * 发送数据全量
     */
    private Integer sendCount;
    /**
     * 发送地址
     */
    private String receiveMessage;
    /**
     * 当前数据全量
     */
    private Integer totalSize;
}
