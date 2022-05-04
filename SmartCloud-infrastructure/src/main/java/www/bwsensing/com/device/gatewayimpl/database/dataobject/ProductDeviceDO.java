package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;
import java.util.List;
import www.bwsensing.com.domain.device.model.ProductModel;

/**
 * @author macos-zyj
 */
@Data
public class ProductDeviceDO {
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
    private List<ExtraProductDataItemDO> extraProductData;

    private String creator;

    private String createTime;
}
