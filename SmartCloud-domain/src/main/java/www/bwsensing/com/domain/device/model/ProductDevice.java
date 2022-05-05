package www.bwsensing.com.domain.device.model;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 产品对应的设备
 * @author macos-zyj
 */
@Data
public class ProductDevice {
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
    private ProductModel sensorModel;

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
     * 额外产品数据
     */
    private List<ExtraProductDataItem> extraProductData;

    /**
     * 设备计算模型关联
     */
    private List<DeviceComputation> deviceComputations;

    private String creator;

    private String createTime;
}
