package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class SensorCO extends DTO {
    private Integer id;
    /**设备Sn码*/
    private String sn;
    /**传感器名称*/
    private String name;
    /**设备型号**/
    private String modelName;
    /**经度**/
    private Double longitude;
    /**维度**/
    private Double latitude;
    /**问题处理人*/
    private String managerName;
    /**管理员手机号*/
    private String managerPhone;
    /**初始提交时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date firstOnlineTime;
    /**
     * 上一次发送时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastSendTime;
    /**电量**/
    private Float electricity;
    /**温度**/
    private Float temperature;
    /**
     * 接收数量
     */
    private Integer lastSendSize;
    /**
     * 数据接收全量
     */
    private Integer totalSendSize;
    /**
     * 接收数据全量
     */
    private Integer lastSendCount;
    /**
     * 发送地址
     */
    private String lastSendAddress;
    /**
     * 数据全量
     */
    private Integer totalSize;
    /**安装时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date assignTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    /**安装时间**/
    private Date  installTime;
    /**创建者**/
    private String createUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Date getFirstOnlineTime() {
        return firstOnlineTime;
    }

    public void setFirstOnlineTime(Date firstOnlineTime) {
        this.firstOnlineTime = firstOnlineTime;
    }

    public Date getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
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

    public Integer getLastSendSize() {
        return lastSendSize;
    }

    public void setLastSendSize(Integer lastSendSize) {
        this.lastSendSize = lastSendSize;
    }

    public Integer getTotalSendSize() {
        return totalSendSize;
    }

    public void setTotalSendSize(Integer totalSendSize) {
        this.totalSendSize = totalSendSize;
    }

    public Integer getLastSendCount() {
        return lastSendCount;
    }

    public void setLastSendCount(Integer lastSendCount) {
        this.lastSendCount = lastSendCount;
    }

    public String getLastSendAddress() {
        return lastSendAddress;
    }

    public void setLastSendAddress(String lastSendAddress) {
        this.lastSendAddress = lastSendAddress;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
