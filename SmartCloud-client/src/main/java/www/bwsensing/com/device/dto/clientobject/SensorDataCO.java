package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@SuppressWarnings("all")
public class SensorDataCO extends DTO {
    private  Integer id;
    /**传感器名称**/
    private String sensorName;
    /**设备Sn码*/
    private String sn;
    /**X轴角度*/
    private String xAngle;
    /**y轴角度*/
    private String yAngle;
    /**z轴角度*/
    private String zAngle;
    /**X轴加速度*/
    private String xAccelerate;
    /**y轴加速度*/
    private String yAccelerate;
    /**z轴加速度*/
    private String zAccelerate;
    /**电量**/
    private Double electricity;
    /**温度**/
    private String temperature;
    private String address;
    private String phone;
    private String ipAddress;
    /**时间戳**/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getxAngle() {
        return xAngle;
    }

    public void setxAngle(String xAngle) {
        this.xAngle = xAngle;
    }

    public String getyAngle() {
        return yAngle;
    }

    public void setyAngle(String yAngle) {
        this.yAngle = yAngle;
    }

    public String getzAngle() {
        return zAngle;
    }

    public void setzAngle(String zAngle) {
        this.zAngle = zAngle;
    }

    public String getxAccelerate() {
        return xAccelerate;
    }

    public void setxAccelerate(String xAccelerate) {
        this.xAccelerate = xAccelerate;
    }

    public String getyAccelerate() {
        return yAccelerate;
    }

    public void setyAccelerate(String yAccelerate) {
        this.yAccelerate = yAccelerate;
    }

    public String getzAccelerate() {
        return zAccelerate;
    }

    public void setzAccelerate(String zAccelerate) {
        this.zAccelerate = zAccelerate;
    }

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
