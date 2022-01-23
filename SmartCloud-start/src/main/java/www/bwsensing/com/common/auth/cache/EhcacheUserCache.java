package www.bwsensing.com.common.auth.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import www.bwsensing.com.common.auth.service.SmartUser;
import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.constant.TokenConstant;
import www.bwsensing.com.common.utills.Md5Utils;
import www.bwsensing.com.common.utills.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author macos-zyj
 */
@Component
public class EhcacheUserCache implements UserCache {
    private static final String USER_NAME_KEY = "PREFIX_";

    @Autowired
    private RedisService redisService;

    @Override
    public UserDetails getUserFromCache(String username) {
        String convertMd5 = redisService.getCacheObject(getSecurityKey(username));
        if (StringUtils.isNotEmpty(convertMd5)){
            String jsonString = Md5Utils.convertMd5(convertMd5);
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            return JSONObject.toJavaObject(jsonObject,SmartUser.class);
        }
        return null;
    }

    @Override
    public void putUserInCache(UserDetails user) {
        String json = JSON.toJSONString(user);
        redisService.setCacheObject(getSecurityKey(user.getUsername()),Md5Utils.convertMd5(json), 1L, TimeUnit.HOURS);
    }

    @Override
    public void removeUserFromCache(String username) {
        redisService.deleteObject(getSecurityKey(username));
    }

    private String getSecurityKey(String username) {
        String key = USER_NAME_KEY+username;
        return Md5Utils.encryptMd5(key);
    }
}
