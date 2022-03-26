package www.bwsensing.com.common.client;

import lombok.Data;
import java.io.Serializable;
import www.bwsensing.com.common.utills.StringUtils;
import org.apache.rocketmq.streams.client.StreamBuilder;
import org.apache.rocketmq.streams.common.functions.MapFunction;
import org.apache.rocketmq.streams.client.source.DataStreamSource;



/**
 * @author macos-zyj
 */
@Data
public class MqttClient<T,O> implements Serializable {
    private static final Integer CLIENT_ID_LENGTH = 10;
    private String url;
    private String clientPrefix;
    private String clientId;
    private String topic;
    private String username;
    private String password;
    private String namespace;
    private String jobName;
    private MapFunction<T,O> mapFunction;

    /**
     * 注意JDK版本号 11 无法运行 需要使用 8 才可
     */
    public void run(){
        if (StringUtils.isEmpty(clientId)){
            clientId = clientPrefix+StringUtils.randomHex(CLIENT_ID_LENGTH);
        }
        DataStreamSource dataStream = StreamBuilder.dataStream(namespace, jobName);
        try{
            dataStream
                    .fromMqtt(url,clientId,topic,username,password)
                    .map(mapFunction)
                    .toFile("../"+namespace+"-"+jobName,true).start();
        } catch (Exception  ex){
            ex.printStackTrace();
        }
    }
}
