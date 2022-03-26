package www.bwsensing.com.common.cache.redis;

import lombok.Builder;
import java.util.function.Function;

/**
 * 分页查询工具类
 * 参考自 Java技术驿站
 * @author macos-zyj
 */
@Builder
public class RedisCacheUtils<P,R> {
    private Function<P, R> cacheObjectFunction;
    private RedisService redisService;

    /**
     * 获取数据库或缓存中的Object 数据
     * @param request
     * @return
     */
    public R getObject(P request,String key) {
        if (null != redisService&&redisService.hasKey(key)){
            return redisService.getCacheObject(key);
        } else{
            R functionResult = cacheObjectFunction.apply(request);
            if (null != redisService){
                redisService.setCacheObject(key,functionResult);
            }
            return functionResult;
        }
    }
}
