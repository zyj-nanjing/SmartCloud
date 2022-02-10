package www.bwsensing.com.common.core.lru;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ConcurrentHashMap;
import www.bwsensing.com.common.utills.Md5Utils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.cache.ehcache.EhCacheService;
/**
 * 推荐单机部署时使用
 *
 * @author macos-zyj
 */
@Slf4j
@Component
public class EhCacheLruService {
    private static final String LRU_CACHE_LRU_PREFIX = "lru_cache_config";
    protected static final String LRU_PREFIX = "LRU_CACHE_";
    protected static final Integer DEFAULT_LRU_SIZE = 10;
    protected static final Integer MAX_CAS_TIMES = 14;
    private static ConcurrentHashMap<String, String> CURRENT_KEY_MAP = new ConcurrentHashMap<>(16);

    @Resource
    protected EhCacheService ehCacheService;

    @Resource
    private AsyncCacheSetTask asyncCacheSetTask;

    public <K, V> void initCacheAndAdd(String bizId, Integer capacity, K key, V value) {
        if (null == ehCacheService.get(LRU_CACHE_LRU_PREFIX, bizId)) {
            synchronized (this) {
                if (null == ehCacheService.get(LRU_CACHE_LRU_PREFIX, bizId)) {
                    LruCache<K, V> cache = new LruCache<>(capacity);
                    cache.put(key, value);
                    ehCacheService.put(LRU_CACHE_LRU_PREFIX, bizId, cache);
                }
            }
        }
    }


    public <K, V> Object getCache(String bizId, String key) {
        LruCache<K, V> cache = (LruCache<K, V>) ehCacheService.get(LRU_CACHE_LRU_PREFIX, bizId);
        if (null != cache) {
            if (null != CURRENT_KEY_MAP.get(bizId) && CURRENT_KEY_MAP.get(bizId).equals(key)) {
                return cache.get(key);
            } else {
                cache.get(key);
                if (compareAndSet(bizId, cache) || CURRENT_KEY_MAP.get(bizId).equals(key)) {
                    CURRENT_KEY_MAP.put(bizId, key);
                    return cache.get(key);
                }
            }
        }
        return null;
    }

    public <K, V> void setCache(String bizId, K key, V value) {
        setCache(bizId, key, value, DEFAULT_LRU_SIZE);
    }

    public <K, V> boolean setCache(String bizId, K key, V value, Integer maxSize) {
        LruCache<K, V> cache = (LruCache<K, V>) ehCacheService.get(LRU_CACHE_LRU_PREFIX, bizId);
        if (null == cache) {
            initCacheAndAdd(bizId, maxSize, key, value);
            return true;
        } else {
            cache.setCapacity(maxSize);
            cache.put(key, value);
            return compareAndSet(bizId, cache);
        }
    }

    public <K, V> boolean removeCache(String bizId, K key) {
        LruCache<K, V> cache = (LruCache<K, V>) ehCacheService.get(LRU_CACHE_LRU_PREFIX, bizId);
        if (null != cache) {
            cache.remove(key);
            return compareAndSet(bizId, cache);
        }
        return false;
    }

    protected String convertRedisKey(String bizId) {
        String redisKey = LRU_PREFIX + bizId;
        return Md5Utils.convertMd5(redisKey);
    }

    protected <K, V> boolean compareAndSet(String bizId, LruCache<K, V> cache) {
        return compareAndSet(bizId, cache, 0);
    }

    protected <K, V> boolean compareAndSet(String bizId, LruCache<K, V> cache, Integer times) {
        boolean needCompare = times < MAX_CAS_TIMES;
        LruCache<K, V> currentCache = (LruCache<K, V>) ehCacheService.get(LRU_CACHE_LRU_PREFIX, bizId);
        if (needCompare) {
            if (!cache.isEquals(currentCache.getVersionCode())) {
                return compareAndSet(bizId, cache, times + 1);
            } else {
                ehCacheService.put(LRU_CACHE_LRU_PREFIX, bizId, cache);
                asyncCacheSetTask.pushCacheChange(bizId, cache.getInnerCache());
                return true;
            }
        } else {
            return syncCompareAndSet(bizId, cache);
        }
    }

    protected synchronized <K, V> boolean syncCompareAndSet(String bizId, LruCache<K, V> cache) {
        LruCache<K, V> currentCache = (LruCache<K, V>) ehCacheService.get(LRU_CACHE_LRU_PREFIX, bizId);
        if (!cache.isEquals(currentCache.getVersionCode())) {
            return false;
        } else {
            ehCacheService.put(LRU_CACHE_LRU_PREFIX, bizId, cache);
            asyncCacheSetTask.pushCacheChange(bizId, cache.getInnerCache());
            return true;
        }
    }
}
