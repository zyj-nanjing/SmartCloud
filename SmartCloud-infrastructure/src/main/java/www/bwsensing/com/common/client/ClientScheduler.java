package www.bwsensing.com.common.client;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.context.annotation.Configuration;
import www.bwsensing.com.common.mqtt.database.MqttStreamMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import www.bwsensing.com.common.mqtt.database.dataobject.MqttStreamConfig;



/**
 * @author macos-zyj
 */
@Slf4j
@Configuration
@EnableScheduling
public class ClientScheduler {

    @Resource
    private MqttStreamMapper mqttStreamMapper;

    @Resource
    private StreamsClientConfiguration clientConfig;


    /**
     * @// TODO: 2022/3/17 后续改成消息通知的形式
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void mqttClientScheduler(){
        List<MqttStreamConfig> configs = mqttStreamMapper.getMqttStreamConfigs();
        if (null != configs && configs.size() >0){
            configs.forEach(config -> clientConfig.addMqttClient(config.getTopic(),config.getNamespace(), config.getJobName()));
        }
    }
}
