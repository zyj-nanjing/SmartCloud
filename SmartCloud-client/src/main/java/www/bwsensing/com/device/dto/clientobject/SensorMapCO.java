package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class SensorMapCO extends DTO {
    /**项目编号*/
    private Integer projectId;
    /***项目名称*/
    private String projectName;
    /**传感器名称*/
    private String sensorName;
    /**设备型号**/
    private String sensorModel;
    /**电量**/
    private Float electricity;
    /**温度**/
    private Float temperature;
    /**在线地址**/
    private String onlineAddress;
    /**经度**/
    private Double longitude;
    /**维度**/
    private Double latitude;
    /**在线状态*/
    private Integer onlineStatus;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorModel() {
        return sensorModel;
    }

    public void setSensorModel(String sensorModel) {
        this.sensorModel = sensorModel;
    }

    public Float getElectricity() {
        return electricity;
    }

    public void setElectricity(Float electricity) {
        this.electricity = electricity;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getOnlineAddress() {
        return onlineAddress;
    }

    public void setOnlineAddress(String onlineAddress) {
        this.onlineAddress = onlineAddress;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
