package www.bwsensing.com.device.dto.clientobject;

import www.bwsensing.com.common.clientobject.DataItemsCO;
import com.alibaba.cola.dto.DTO;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductMapDetailCO extends DTO {
    /**项目编号*/
    private Integer projectId;
    /***项目名称*/
    private String projectName;
    /**设备唯一编码*/
    private String uniqueCode;
    /**传感器名称*/
    private String deviceName;
    /**设备型号**/
    private String deviceModel;
    /**经度**/
    private Double longitude;
    /**维度**/
    private Double latitude;
    /**在线状态*/
    private Integer onlineStatus;

    /**
     * 传感器对应的数据项
     */
    private List<DataItemsCO> dataItems;

    /**
     * 设备当前数据
     */
    public Map<String,String> deviceCurrentData;

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

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
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

    public List<DataItemsCO> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<DataItemsCO> dataItems) {
        this.dataItems = dataItems;
    }

    public Map<String, String> getDeviceCurrentData() {
        return deviceCurrentData;
    }

    public void setDeviceCurrentData(Map<String, String> deviceCurrentData) {
        this.deviceCurrentData = deviceCurrentData;
    }
}
