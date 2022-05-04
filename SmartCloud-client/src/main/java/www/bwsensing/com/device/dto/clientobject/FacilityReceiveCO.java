package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import java.util.Date;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class FacilityReceiveCO extends DTO {
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
     * 当前数据全量
     */
    private Integer totalSize;
    /**
     * 发送地址
     */
    private String receiveMessage;

    public FacilityReceiveCO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getReceiveSize() {
        return receiveSize;
    }

    public void setReceiveSize(Integer receiveSize) {
        this.receiveSize = receiveSize;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public String getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(String receiveMessage) {
        this.receiveMessage = receiveMessage;
    }
}
