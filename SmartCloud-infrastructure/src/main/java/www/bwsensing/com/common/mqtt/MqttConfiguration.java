package www.bwsensing.com.common.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
@Configuration
@ConfigurationProperties(MqttConfiguration.PREFIX)
public class MqttConfiguration {

    @Resource
    private MqttPushClient mqttPushClient;

    public static final  String PREFIX="spring.mqtt";
    private String host;
    private String clientId;
    private String clientPrefix;
    private String username;
    private String password;
    private String topic;
    private int timeout;
    private int keepalive;

    @Bean
    public MqttPushClient getMqttPushClient() {
        mqttPushClient.connect(host, clientId, username, password, timeout,keepalive);
        // 以/#结尾表示订阅所有以topic开头的主题
        mqttPushClient.subscribe(topic+"/#", 0);
        return mqttPushClient;
    }

    public void setMqttPushClient(MqttPushClient mqttPushClient) {
        this.mqttPushClient = mqttPushClient;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepalive() {
        return keepalive;
    }

    public String getClientPrefix() {
        return clientPrefix;
    }

    public void setClientPrefix(String clientPrefix) {
        this.clientPrefix = clientPrefix;
    }

    public void setKeepalive(int keepalive) {
        this.keepalive = keepalive;
    }

    @Override
    public String toString() {
        return "MqttConfiguration{" + "mqttPushClient=" + mqttPushClient +
                ", host='" + host + '\'' +
                ", clientId='" + clientId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", topic='" + topic + '\'' +
                ", timeout=" + timeout +
                ", keepalive=" + keepalive +
                '}';
    }
}