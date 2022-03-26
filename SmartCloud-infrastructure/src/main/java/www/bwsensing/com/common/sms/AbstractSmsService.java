package www.bwsensing.com.common.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author macos-zyj
 */
public abstract class AbstractSmsService implements ISmsService {

    private ConcurrentHashMap<String, Client> acsClientConcurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public Client getClient(String accessKeyId,
                            String accessKeySecret) {

        return acsClientConcurrentHashMap.computeIfAbsent(
                getKey("cn-hangzhou", accessKeyId, accessKeySecret),
                (iClient) -> {
                    Config config = new Config();
                    config.accessKeyId = accessKeyId;
                    config.accessKeySecret = accessKeySecret;
                    try {
                        return new Client(config);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    private String getKey(String regionId, String accessKeyId, String accessKeySecret) {

        return regionId + ":" + accessKeyId + ":" + accessKeySecret;
    }

}
