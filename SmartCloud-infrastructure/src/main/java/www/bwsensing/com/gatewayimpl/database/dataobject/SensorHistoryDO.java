package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class SensorHistoryDO {
    private Integer id;
    private String ipAddress;
    private String channelId;
    private String snCode;
    private Date onlineTime;
    private Date relineTime;
    private Date refreshTime;
    private Date offLineTime;
}
