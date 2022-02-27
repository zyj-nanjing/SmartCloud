package www.bwsensing.com.common.client;

import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import www.bwsensing.com.common.mqtt.MqttConfiguration;
import www.bwsensing.com.common.thread.NamedThreadFactory;

/**
 * @author macos-zyj
 */
@Slf4j
@Profile("test")
@Configuration
public class StreamsClientConfiguration{
    /**
     * 默认线程池
     *     如果处理器无定制线程池，则使用此默认
     */
    private static final ExecutorService STREAM_EXECUTOR = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1000),
            new NamedThreadFactory("ASYNC-CLIENT-STREAM-POOL"));

    private static final ExecutorService EXECUTE_EXECUTOR = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1000),
            new NamedThreadFactory("ASYNC-STREAM-EXECUTE-POOL"));

    @Resource
    private MqttConfiguration config;

    @Resource
    private ClientUserFunction clientUserFunction;

    @Resource
    private ClientStreamExecute streamExecute;


    public  void addMqttClient(String topic,String namespace,String jobName){
        MqttClient client = new MqttClient();
        client.setUrl(config.getHost());
        client.setClientId(config.getClientId());
        client.setUsername(config.getUsername());
        client.setPassword(config.getPassword());
        client.setTopic(topic);
        client.setNamespace(namespace);
        client.setJobName(jobName);
        client.setMapFunction(clientUserFunction.getUserFunction(namespace, jobName));
        STREAM_EXECUTOR.submit(client::run);
        EXECUTE_EXECUTOR.submit(streamExecute::execute);
    }

}
