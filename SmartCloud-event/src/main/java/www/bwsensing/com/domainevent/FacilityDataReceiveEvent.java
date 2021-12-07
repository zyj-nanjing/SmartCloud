package www.bwsensing.com.domainevent;

import lombok.Data;
import java.util.Date;
import java.util.List;
import www.bwsensing.com.domainevent.object.DataMessage;
import www.bwsensing.com.event.DomainEventI;


/**
 * @author macos-zyj
 */
@Data
public class FacilityDataReceiveEvent implements DomainEventI {
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
     * 发送地址
     */
    private String sendAddress;

    /**
     * 接收的数据
     */
    private List<DataMessage> receiveData;
}
