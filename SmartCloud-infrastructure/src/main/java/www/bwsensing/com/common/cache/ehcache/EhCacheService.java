package www.bwsensing.com.common.cache.ehcache;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import www.bwsensing.com.common.constant.EhCacheConstant;
import org.springframework.stereotype.Component;
import net.sf.ehcache.CacheManager;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Element;
import net.sf.ehcache.Cache;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * spring EhCache 工具类
 *
 * @author beiwei
 **/
@SuppressWarnings(value = {"unchecked"})
@Component
@Slf4j
public class EhCacheService {

    @Resource
    private EhCacheCacheManager ehCacheManager;


    /**
     * 获取SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return get(EhCacheConstant.CACHE_PREFIX, key);
    }

    /**
     * 获取可失效缓存
     *
     * @param key
     * @return
     */
    public Object getTemp(String key){
        return get(EhCacheConstant.TEMP_CACHE_PREFIX, key);
    }

    /**
     * 获取SYS_CACHE缓存
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get(String key, Object defaultValue) {
        Object value = get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 写入SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public void put(String key, Object value) {
        put(EhCacheConstant.CACHE_PREFIX, key, value);
    }

    /**
     * 写入可失效缓存
     *
     * @param key
     * @return
     */
    public void putTemp(String key, Object value) {
        put(EhCacheConstant.TEMP_CACHE_PREFIX, key, value);
    }


    /**
     * 从SYS_CACHE缓存中移除
     *
     * @param key
     * @return
     */
    public void remove(String key) {
        remove(EhCacheConstant.CACHE_PREFIX, key);
    }

    /**
     * 删除可失效缓存
     *
     * @param key
     * @return
     */
    public void removeTemp(String key){
        remove(EhCacheConstant.TEMP_CACHE_PREFIX, key);
    }
    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(getKey(key));
        if (null == element ){
            return null;
        } else {
            return element.getObjectValue();
        }
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get(String cacheName, String key, Object defaultValue) {
        Object value = get(cacheName, getKey(key));
        return value != null ? value : defaultValue;
    }

    /**
     * 写入缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        Element element = new Element(getKey(key), value);
        getCache(cacheName).put(element);
    }

    /**
     * 写入缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value,Boolean doNotNotifyCacheReplicators) {
        Element element = new Element(getKey(key), value);
        getCache(cacheName).put(element,doNotNotifyCacheReplicators);
    }


    /**
     * 从缓存中移除
     *
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key) {
        getCache(cacheName).remove(getKey(key));
    }

    /**
     * 从缓存中移除所有
     *
     * @param cacheName
     */
    public void removeAll(String cacheName) {
        Cache cache = getCache(cacheName);
        List<String> keys = cache.getKeys();
        for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
            cache.remove(it.next());
        }
        log.info("清理缓存： {} => {}", cacheName, keys);
    }

    /**
     * 从缓存中移除指定key
     *
     * @param keys
     */
    public void removeByKeys(Set<String> keys) {
        removeByKeys(EhCacheConstant.CACHE_PREFIX, keys);
    }

    /**
     * 从缓存中移除指定key
     *
     * @param cacheName
     * @param keys
     */
    public void removeByKeys(String cacheName, Set<String> keys) {
        for (String key : keys) {
            remove(key);
        }
        log.info("清理缓存： {} => {}", cacheName, keys);
    }

    /**
     * 获取缓存键名
     *
     * @param key
     * @return
     */
    private String getKey(String key) {
        return key;
    }

    public  Cache getCache(String cacheName){
        CacheManager cacheManager = ehCacheManager.getCacheManager();
        assert cacheManager != null;
        return cacheManager.getCache(cacheName);
    }
}
