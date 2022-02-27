package www.bwsensing.com.common.client;

import java.io.Serializable;
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
@Profile("test")
@Configuration
@EnableScheduling
public class ClientScheduler {

    @Resource
    private MqttStreamMapper mqttStreamMapper;

    @Resource
    private StreamsClientConfiguration clientConfig;

    @Scheduled(cron = "0 */10 * * * ?")
    public void mqttClientScheduler(){
        log.info("------------同步客户端流式处理信息--------------");
        List<MqttStreamConfig> configs = mqttStreamMapper.getMqttStreamConfigs();
        if (null != configs && configs.size() >0){
            configs.forEach(config -> clientConfig.addMqttClient(config.getTopic(),config.getNamespace(), config.getJobName()));
        }
    }
}
