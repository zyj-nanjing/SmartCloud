package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class ServiceDeploy {
    /** 主键 */
    private Integer id;

    /** 主机名称 */
    private String hostName;

    /** 权级 */
    private Long weight;

    /** 地理区域 */
    private String location;

    /** 配置 */
    private String configure;

    /** IP地址 */
    private String ipv4;

    /** 内部IP地址(单地区部署尽量同网段部署，多部署需要避免两地网段一致) */
    private String ipv4Inner;

    private Boolean isHealthy;

    /** 备注 */
    private String remark;

    public ServiceDeploy() {
    }

    public ServiceDeploy(String hostName, String ipv4Inner) {
        this.hostName = hostName;
        this.ipv4Inner = ipv4Inner;
    }
}
