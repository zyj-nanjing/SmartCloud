package www.bwsensing.com.common.mqtt.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class MqttStreamConfig {
    private Integer id;
    private String topic;
    private String configId;
    private String namespace;
    private String jobName;
}
