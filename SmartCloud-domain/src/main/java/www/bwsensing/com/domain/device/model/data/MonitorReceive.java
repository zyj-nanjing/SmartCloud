package www.bwsensing.com.domain.device.model.data;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 消息接收
 * @author macos-zyj
 */
@Data
public class MonitorReceive {
    /**
     * Sn编码
     */
    private String sn;
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

    private String phoneNumber;

    private Float electricity;

    private Float temperature;
    /**
     * 接收数量
     */
    private Integer receiveSize;
    /**
     * 发送数据全量
     */
    private Integer sendCount;
    /**
     * 发送消息
     */
    private String receiveMessage;
    /**
     * 当前数据全量
     */
    private Integer totalSize;
    /**
     * 数据集
     */
    private List<MonitorData> dataCollection;

}
