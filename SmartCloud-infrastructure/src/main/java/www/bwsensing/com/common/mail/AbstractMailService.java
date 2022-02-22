package www.bwsensing.com.common.mail;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author macos-zyj
 */
public abstract class AbstractMailService implements IMailService{
    private final ConcurrentHashMap<String, IAcsClient> acsClientConcurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public IAcsClient getRegionClientProfile(String accessKeyId,
                                             String accessKeySecret) {
        return getRegionClientProfile(accessKeyId, accessKeySecret,"cn-hangzhou");
    }

    @Override
    public IAcsClient getRegionClientProfile(String accessKeyId,
                                             String accessKeySecret,
                                             String location) {
        return acsClientConcurrentHashMap.computeIfAbsent(
                getKey(location, accessKeyId, accessKeySecret),
                (iClient) -> new DefaultAcsClient(DefaultProfile
                        .getProfile(location, accessKeyId, accessKeySecret)));
    }

    private String getKey(String regionId, String accessKeyId, String accessKeySecret) {

        return regionId + ":" + accessKeyId + ":" + accessKeySecret;
    }

}
