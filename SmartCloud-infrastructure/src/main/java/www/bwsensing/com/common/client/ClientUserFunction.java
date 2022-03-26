package www.bwsensing.com.common.client;

import org.apache.rocketmq.streams.common.functions.MapFunction;
import java.io.Serializable;

/**
 * @author macos-zyj
 */
public interface ClientUserFunction<O> extends Serializable {

    /**
     * 获取用户自定义逻辑
     * @param namespace
     * @param jobName
     * @return
     */
    MapFunction<String,O> getUserFunction(String namespace,String jobName);
}
