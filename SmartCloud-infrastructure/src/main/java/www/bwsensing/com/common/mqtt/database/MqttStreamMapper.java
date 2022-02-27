package www.bwsensing.com.common.mqtt.database;

import www.bwsensing.com.common.mqtt.database.dataobject.MqttStreamConfig;
import java.util.List;

/**
 * MQTT流
 * @author macos-zyj
 */
public interface MqttStreamMapper {

    /**
     * 获取相关Mqtt 配置
     * @return
     */
    List<MqttStreamConfig> getMqttStreamConfigs();
}
