package www.bwsensing.com.common.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

/**
 * @author macos-zyj
 * @Classname PushCallback
 * @Description 消费监听类
 */
@Slf4j
@Component
public class PushCallback implements MqttCallback {

    @Autowired
    private MqttConfiguration mqttConfiguration;

    private static MqttClient client;

    @Override
    public void connectionLost(Throwable throwable) {
        if (client == null || !client.isConnected()) {
            log.warn("连接断开，正在重连....");
            mqttConfiguration.getMqttPushClient();
            client = MqttPushClient.getClient();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        log.info("接收消息主题 :{}", topic);
        log.info("接收消息Qos :{}" ,message.getQos());
        log.info("接收消息内容 :{}",new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.warn("deliveryComplete---------:{}",token.isComplete());
    }
}
