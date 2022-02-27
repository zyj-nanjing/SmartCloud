package www.bwsensing.com.common.client;

import lombok.Data;
import org.apache.rocketmq.streams.client.StreamBuilder;
import org.apache.rocketmq.streams.common.functions.MapFunction;
import org.apache.rocketmq.streams.client.source.DataStreamSource;
import java.io.Serializable;

/**
 * @author macos-zyj
 */
@Data
public class MqttClient<T,O> implements Serializable {
    private String url;
    private String clientId;
    private String topic;
    private String username;
    private String password;
    private String namespace;
    private String jobName;
    private MapFunction<T,O> mapFunction;

    public void run(){
        DataStreamSource dataStream = StreamBuilder.dataStream(namespace, jobName);
        dataStream
                .fromMqtt(url,clientId,topic,username,password)
                .map(mapFunction)
                .toFile("../"+namespace+"-"+jobName,true).start();
    }
}
