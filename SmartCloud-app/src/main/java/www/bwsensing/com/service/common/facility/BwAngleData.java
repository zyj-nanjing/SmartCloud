package www.bwsensing.com.service.common.facility;

import lombok.Data;
import www.bwsensing.com.domain.device.data.MonitorData;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class BwAngleData {
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

    public List<MonitorData> toSeriesData(){
        List<MonitorData> monitorArray = new ArrayList<>();
        if ( null != this.getXAngle()){
            monitorArray.add(initData("x_ang",this.getXAngle()));
        }
        if ( null != this.getYAngle()){
            monitorArray.add(initData("y_ang",this.getYAngle()));
        }
        if ( null != this.getZAngle()){
            monitorArray.add(initData("z_ang",this.getZAngle()));
        }
        if ( null != this.getXAccelerate()){
            monitorArray.add(initData("x_acc",this.getXAccelerate()));
        }
        if ( null != this.getYAccelerate()){
            monitorArray.add(initData("y_acc",this.getYAccelerate()));
        }
        if ( null != this.getZAccelerate()){
            monitorArray.add(initData("z_acc",this.getZAccelerate()));
        }
        if ( null != this.getElectricity()){
            monitorArray.add(initData("elect",this.getElectricity()));
        }
        if ( null != this.getTemperature()){
            monitorArray.add(initData("temp",this.getTemperature()));
        }
        return monitorArray;
    }

    private MonitorData initData(String dataId, float value){
        if (value> MAX_NUMBER || value < MIN_VALUE){
            throw new IllegalArgumentException("传感器接收数据有误!");
        }
        MonitorData monitorData = new MonitorData();
        monitorData.setSn(this.getSn());
        monitorData.setGroupId(101);
        monitorData.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        monitorData.setType("avg");
        monitorData.setDataId(dataId);
        monitorData.setDataIdValue(value);
        return monitorData;
    }
}
