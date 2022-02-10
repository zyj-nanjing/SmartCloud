package www.bwsensing.com.common.core.lru;

import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.utills.Md5Utils;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class RedisLruCache {
    protected static final String  LRU_PREFIX = "LRU_CACHE_";
    protected static final Integer DEFAULT_LRU_SIZE = 10;
    protected static final Integer MAX_CAS_TIMES = 14;
    private static ConcurrentHashMap<String,String> CURRENT_KEY_MAP = new ConcurrentHashMap<>(16);

    @Resource
    protected RedisService redisService;

    @Resource
    private AsyncCacheSetTask asyncCacheSetTask;

    public<K,V> void initCacheAndAdd(String bizId, Integer capacity, K key, V value) {
       String redisKey = convertRedisKey(bizId);
       if(null == redisService.getCacheObject(redisKey)){
           synchronized (this){
               if(null == redisService.getCacheObject(redisKey)) {
                   LruCache<K,V> cache = new LruCache<>(capacity);
                   cache.put(key, value);
                   redisService.setCacheObject(redisKey,cache,30L, TimeUnit.DAYS);
               }
           }
       }
    }


    public  <K,V>Object getCache(String bizId, String key){
        String redisKey = convertRedisKey(bizId);
        LruCache<K,V> cache =  redisService.getCacheObject(redisKey);
        if(null != cache){
//            if (null != CURRENT_KEY_MAP.get(bizId)&&CURRENT_KEY_MAP.get(bizId).equals(key)){
//                return cache.get(key);
//            } else {
//                cache.get(key);
//                if (compareAndSet(bizId, cache) || CURRENT_KEY_MAP.get(bizId).equals(key)) {
//                    CURRENT_KEY_MAP.put(bizId, key);
//                    return cache.get(key);
//                }
//            }
            cache.get(key);
            if (compareAndSet(bizId, cache) || CURRENT_KEY_MAP.get(bizId).equals(key)) {
                CURRENT_KEY_MAP.put(bizId, key);
                return cache.get(key);
            }
        }
        return null;
    }

    public<K,V> void setCache(String bizId,K key,V value){
        setCache(bizId,key,value,DEFAULT_LRU_SIZE);
    }

    public<K,V> boolean setCache(String bizId,K key,V value,Integer maxSize){
        String redisKey = convertRedisKey(bizId);
        LruCache<K,V> cache =  redisService.getCacheObject(redisKey);
        if(null == cache) {
            initCacheAndAdd(bizId,maxSize,key,value);
            return true;
        } else {
            cache.setCapacity(maxSize);
            cache.put(key, value);
            return compareAndSet(bizId, cache);
        }
    }

    public<K,V> boolean removeCache(String bizId,K key){
        String redisKey = convertRedisKey(bizId);
        LruCache<K,V> cache =  redisService.getCacheObject(redisKey);
        if(null != cache) {
            cache.remove(key);
            return compareAndSet(bizId, cache);
        }
        return false;
    }

    protected String convertRedisKey(String bizId){
        String redisKey = LRU_PREFIX + bizId;
        return Md5Utils.convertMd5(redisKey);
    }

    protected<K,V> boolean compareAndSet(String bizId,LruCache<K,V> cache){
        return compareAndSet(bizId,cache,0);
    }

    protected<K,V> boolean compareAndSet(String bizId,LruCache<K,V> cache,Integer times){
        boolean needCompare = times < MAX_CAS_TIMES;
        String redisKey = convertRedisKey(bizId);
        LruCache<K,V> currentCache =  redisService.getCacheObject(redisKey);
        if(needCompare){
            if (!cache.isEquals(currentCache.getVersionCode())) {
                return compareAndSet(bizId,cache,times+1);
            } else {
                redisService.setCacheObject(convertRedisKey(bizId),cache,30L, TimeUnit.DAYS);
                asyncCacheSetTask.pushCacheChange(bizId,cache.getInnerCache());
                return true;
            }
        } else {
            return syncCompareAndSet(bizId,cache);
        }
    }

    protected synchronized <K,V>  boolean syncCompareAndSet(String bizId,LruCache<K,V> cache){
        String redisKey = convertRedisKey(bizId);
        LruCache<K,V> currentCache =  redisService.getCacheObject(redisKey);
        if (!cache.isEquals(currentCache.getVersionCode())) {
            return false;
        } else {
            redisService.setCacheObject(convertRedisKey(bizId),cache,30L, TimeUnit.DAYS);
            asyncCacheSetTask.pushCacheChange(bizId,cache.getInnerCache());
            return true;
        }
    }
}
