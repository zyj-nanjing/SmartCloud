package www.bwsensing.com.device.dto.command;

import lombok.Data;
import java.util.Date;
import java.util.Map;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 保存传感器
 * @author macos-zyj
 */
@Data
public class ProductSaveCmd extends Command {
    private static final long serialVersionUID = 2131212213975403995L;
    /**
     * 设备唯一编号
     */
    @NotBlank(message = "设备唯一编号不能为空!")
    private String uniqueCode;;
    /**传感器名称*/
    @NotBlank(message = "传感器名称不能为空!")
    private String name;
    /**设备模板**/
    @NotNull(message ="传感器模型编号不能为空!")
    private Integer modelId;
    /**设备编码*/
    private String deviceNo;
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
    @NotNull(message ="请选择是否需要经纬度!")
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
