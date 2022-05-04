package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductDeviceCO extends DTO {
    private Integer id;
    /**
     * 设备Sn码
     */
    private String uniqueCode;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 组织编码(用于关联客户及组织关系)
     */
    private Integer organizationId;
    /**
     * 模型编号
     */
    private Integer modelId;
    /**
     * 测点编号
     */
    private Integer positionId;
    /**
     * 项目编号
     */
    private Integer projectId;
    /**
     * 产品型号
     **/
    private Integer sensorModel;
    /**
     * 通讯编码
     */
    private String communicationCode;

    /**
     * 生产时间
     */
    private Date productionTime;

    /**
     * 硬件版本
     */
    private Float hardwareVersion;

    /**
     * 软件版本
     */
    private Float softwareVersion;

    /**
     * 安装时间
     **/
    private Date  installTime;
    /**
     * 是否需要经纬度
     */
    private Boolean needSituation;

    /***
     * 经度
     */
    private Double longitude;
    /**
     * 维度
     */
    private Double latitude;

    /**
     * 额外产品数据信息
     */
    private List<ExtraProductDataItemCO> extraProductData;

    private String creator;

    private String createTime;

    public ProductDeviceCO() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSensorModel() {
        return sensorModel;
    }

    public void setSensorModel(Integer sensorModel) {
        this.sensorModel = sensorModel;
    }

    public String getCommunicationCode() {
        return communicationCode;
    }

    public void setCommunicationCode(String communicationCode) {
        this.communicationCode = communicationCode;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Date productionTime) {
        this.productionTime = productionTime;
    }

    public Float getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(Float hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public Float getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(Float softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Boolean getNeedSituation() {
        return needSituation;
    }

    public void setNeedSituation(Boolean needSituation) {
        this.needSituation = needSituation;
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

    public List<ExtraProductDataItemCO> getExtraProductData() {
        return extraProductData;
    }

    public void setExtraProductData(List<ExtraProductDataItemCO> extraProductData) {
        this.extraProductData = extraProductData;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
