package www.bwsensing.com.common.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import com.xxl.mq.client.consumer.MqResult;
import com.xxl.mq.client.consumer.IMqConsumer;
import org.springframework.stereotype.Service;
import com.xxl.mq.client.consumer.annotation.MqConsumer;

/**
 * @author macos-zyj
 */
@MqConsumer(topic = "MQTT_CLIENT")
@Service
public class ClientServiceConsumer implements IMqConsumer {
    private final Logger logger = LoggerFactory.getLogger(ClientServiceConsumer.class);

    @Resource
    private ClientCheckService clientCheckService;

    @Override
    public MqResult consume(String data) {
        clientCheckService.mqttClientCheck();
        logger.info("[ClientServiceConsumer]:{}", data);
        return MqResult.SUCCESS;
    }
}
