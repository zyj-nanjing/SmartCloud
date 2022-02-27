package www.bwsensing.com.common.client.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.common.client.database.dataobject.DataStreamSource;

/**
 * @author macos-zyj
 */
public interface DataStreamSourceMapper {

    /**
     * 保存数据流配置
     * @param streamSource
     */
    void saveDataStream(DataStreamSource streamSource);

    /**
     * 关联配置与Mqtt关联
     * @param topic
     * @param configId
     */
    void insertMqttTopic(@Param("topic")String topic,@Param("configId") Integer configId);

    /**
     * 校验对应配置是否存在
     * @param namespace
     * @param jobName
     * @return
     */
    DataStreamSource queryDataStreamSource(@Param("namespace")String namespace,@Param("jobName")String jobName);


    /**
     * 校验Mqtt主题配置是否存在
     * @param topic
     * @param configId
     * @return
     */
    Integer checkMqttTopicExist(@Param("topic")String topic,@Param("configId") Integer configId);
}
