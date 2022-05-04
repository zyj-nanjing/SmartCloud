package www.bwsensing.com.device.dto.command;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * @author macos-zyj
 */
@Data
public class ProductUpdateCmd {
    @NotNull(message = "主键不能为空")
    private Integer id;
    /**传感器名称*/
    private String name;
    /**设备编码*/
    private String deviceNo;
    /**设备模板**/
    private Integer modelId;
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
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 维度
     */
    private Double latitude;

    /**
     * 设备额外配置信息
     */
    private Map<Integer, String> extraData;
}
