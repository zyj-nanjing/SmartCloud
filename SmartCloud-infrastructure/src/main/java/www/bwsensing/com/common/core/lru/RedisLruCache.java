package www.bwsensing.com.common.core.lru;

import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.utills.Md5Utils;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class RedisLruCache {
    protected static final String  LRU_PREFIX = "LRU_";
    protected static final Integer DEFAULT_LRU_SIZE = 10;
    protected static final Integer MAX_CAS_TIMES = 15;

    @Resource
    protected RedisService redisService;

    @Resource
    private AsyncCacheSetTask asyncCacheSetTask;

    public<K,V> void initCacheAndAdd(String bizId, Integer capacity, K key, V value) {
       String redisKey = convertRedisKey(bizId);
       if(null == redisService.getCacheObject(redisKey)){
           synchronized (this){
               if(null == redisService.getCacheObject(redisKey)) {
                   LruLinkedCacheMap<K,V> cache = new LruLinkedCacheMap<>(capacity);
                   cache.put(key, value);
                   redisService.setCacheObject(convertRedisPushKey(bizId),cache.getUuidVersionCode(),30L, TimeUnit.DAYS);
                   redisService.setCacheObject(redisKey,cache,30L, TimeUnit.DAYS);
               }
           }
       }
    }


    public Object getCache(String bizId, String key){
        return getCache(bizId, key, 0);
    }


    public <K,V> Object getCache(String bizId, String key,Integer times){
        if(times > MAX_CAS_TIMES){
            return null;
        }
        String redisKey = convertRedisKey(bizId);
        LruLinkedCacheMap<K,V> cache =  redisService.getCacheObject(redisKey);
        if(null != cache){
            Long codeValue = redisService.getCacheObject(convertRedisPushKey(bizId));
            Object result = cache.get(key);
            if(compareAndSet(bizId, codeValue,cache)){
                return result;
            } else {
                return getCache(bizId, key, times+1);
            }
        }
        return null;
    }

    public<K,V> void setCache(String bizId,K key,V value){
        setCache(bizId,key,value,0,DEFAULT_LRU_SIZE);
    }

    public<K,V> void setCache(String bizId,K key,V value,Integer maxSize){
        setCache(bizId,key,value,0,maxSize);
    }

    public<K,V> void removeCache(String bizId,K key){
        removeCache(bizId,key,0);
    }

    private<K,V> void removeCache(String bizId,K key,Integer times){
        if(times > MAX_CAS_TIMES){
            return;
        }
        String redisKey = convertRedisKey(bizId);
        LruLinkedCacheMap<K,V> cache =  redisService.getCacheObject(redisKey);
        if(null != cache) {
            cache.remove(key);
            Long timestampCode = redisService.getCacheObject(convertRedisPushKey(bizId));
            if(!compareAndSet(bizId, timestampCode,cache)){
                removeCache(bizId, key, times+1);
            }
        }
    }

    private<K,V> void setCache(String bizId,K key,V value,Integer times,Integer maxSize){
        if(times > MAX_CAS_TIMES){
            return;
        }
        String redisKey = convertRedisKey(bizId);
        LruLinkedCacheMap<K,V> cache =  redisService.getCacheObject(redisKey);
        if(null == cache) {
            initCacheAndAdd(bizId,maxSize,key,value);
        } else {
            cache.setCapacity(maxSize);
            Long timestampCode = redisService.getCacheObject(convertRedisPushKey(bizId));
            cache.put(key, value);
            if(!compareAndSet(bizId, timestampCode,cache)){
                setCache(bizId, key, value, times+1,maxSize);
            }
        }
    }

    protected String convertRedisKey(String bizId){
        String redisKey = LRU_PREFIX + bizId;
        return Md5Utils.convertMd5(redisKey);
    }


    protected String convertRedisPushKey(String bizId){
        String redisKey = LRU_PREFIX+"PUSH_" + bizId;
        return Md5Utils.convertMd5(redisKey);
    }


    protected boolean compare(String redisPushKey,Long expectedValue) {
        //读取Redis中的值
        long oldValue = redisService.getCacheObject(redisPushKey);
        log.info("redis value:{}.expect value:{}",oldValue,expectedValue);
        return expectedValue.equals(oldValue);
    }

    protected<K,V> boolean compareAndSet(String bizId, Long oldVersion, LruLinkedCacheMap<K,V> cache){
        String redisPushKey = convertRedisPushKey(bizId);
        if (!compare(redisPushKey,oldVersion)) {
            return false;
        }
        redisService.setCacheObject(convertRedisPushKey(bizId),cache.getUuidVersionCode(),30L, TimeUnit.DAYS);
        redisService.setCacheObject(convertRedisKey(bizId),cache,30L, TimeUnit.DAYS);
        asyncCacheSetTask.pushCacheChange(bizId,cache);
        return true;
    }
}
