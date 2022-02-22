package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class SensorDataDO {
    private  Integer id;
    private Integer structureId;
    private String raw;
    /**设备Sn码*/
    private String sn;
    /**X轴角度*/
    private Float xAngle;
    /**y轴角度*/
    private Float yAngle;
    /**z轴角度*/
    private Float zAngle;
    /**X轴加速度*/
    private Float xAccelerate;
    /**y轴加速度*/
    private Float yAccelerate;
    /**z轴加速度*/
    private Float zAccelerate;
    /**电量**/
    private Float electricity;
    /**温度**/
    private Float temperature;
    private String address;
    private String phone;
    private String ipAddress;
    /**时间戳**/
    private Date timestamp;
    /**查询起始时间**/
    private Date startTime;
    /**查询结束时间**/
    private Date endTime;
}
