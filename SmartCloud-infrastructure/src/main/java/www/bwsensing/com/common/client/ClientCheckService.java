package www.bwsensing.com.common.client;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import www.bwsensing.com.common.mqtt.database.MqttStreamMapper;
import www.bwsensing.com.common.mqtt.database.dataobject.MqttStreamConfig;



/**
 * @author macos-zyj
 */
@Slf4j
@Configuration
public class ClientCheckService {

    @Resource
    private MqttStreamMapper mqttStreamMapper;

    @Resource
    private StreamsClientConfiguration clientConfig;

    public void mqttClientCheck(){
        List<MqttStreamConfig> configs = mqttStreamMapper.getMqttStreamConfigs();
        if (null != configs && configs.size() >0){
            configs.forEach(config -> clientConfig.addMqttClient(config.getTopic(),config.getNamespace(), config.getJobName()));
        }
    }
}
