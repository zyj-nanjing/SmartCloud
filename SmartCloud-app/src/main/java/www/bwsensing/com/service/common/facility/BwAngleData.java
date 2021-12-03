package www.bwsensing.com.service.common.facility;

import lombok.Data;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import www.bwsensing.com.domain.device.data.MonitorData;
/**
 * @author macos-zyj
 */
@Data
public class BwAngleData {
    private static final Integer SUB = 100;
    private static final Integer MAX_NUMBER = 360;
    private static final Integer MIN_VALUE = -360;
    private Integer id;
    private String raw;
    /**设备Sn码*/
    private String sn;
    private Integer groupId;
    /**连接Channel*/
    private String channelId;
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
    private String phoneNumber;
    private String ip;
    /**时间戳**/
    private Date timestamp;
    private boolean isHexDecode;

    public List<MonitorData> toSeriesData(int index){
        List<MonitorData> monitorArray = new ArrayList<>();
        if ( null != this.getXAngle()){
            monitorArray.add(initData("x_ang",this.getXAngle(),index));
        }
        if ( null != this.getYAngle()){
            monitorArray.add(initData("y_ang",this.getYAngle(),index));
        }
        if ( null != this.getZAngle()){
            monitorArray.add(initData("z_ang",this.getZAngle(),index));
        }
        if ( null != this.getXAccelerate()){
            monitorArray.add(initData("x_acc",this.getXAccelerate(),index));
        }
        if ( null != this.getYAccelerate()){
            monitorArray.add(initData("y_acc",this.getYAccelerate(),index));
        }
        if ( null != this.getZAccelerate()){
            monitorArray.add(initData("z_acc",this.getZAccelerate(),index));
        }
        if ( null != this.getElectricity()){
            monitorArray.add(initData("elect",this.getElectricity(),index));
        }
        if ( null != this.getTemperature()){
            monitorArray.add(initData("temp",this.getTemperature(),index));
        }
        return monitorArray;
    }

    private MonitorData initData(String dataId, float value,int index){
        if (value> MAX_NUMBER || value < MIN_VALUE){
            throw new IllegalArgumentException("传感器接收数据有误!");
        }
        MonitorData monitorData = new MonitorData();
        monitorData.setSn(this.getSn());
        monitorData.setGroupId(101);
        long currentTime = System.currentTimeMillis()+ (long) index * SUB;
        monitorData.setTimeStamp(new Timestamp(currentTime));
        monitorData.setType("avg");
        monitorData.setDataId(dataId);
        monitorData.setDataIdValue(value);
        return monitorData;
    }
}
