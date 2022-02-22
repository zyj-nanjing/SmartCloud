package www.bwsensing.com.common.scheduled;

import net.sf.ehcache.Cache;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import net.sf.ehcache.CacheManager;
import www.bwsensing.com.common.annotation.BizScheduled;
import www.bwsensing.com.common.constant.EhCacheConstant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;



/**
 * 定时清除过期本地缓存
 * @author macos-zyj*/
@Slf4j
@Configuration
@EnableScheduling
public class ExpireLocalCacheScheduler {

    @Resource
    private EhCacheCacheManager ehCacheManager;

    /**
     * 10分钟清除一次 系统缓存
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void expiredLocalCache() {
        try {
            CacheManager cacheManager = ehCacheManager.getCacheManager();
            assert cacheManager != null;
            Cache cache = cacheManager.getCache(EhCacheConstant.CACHE_PREFIX);
            cache.getKeysWithExpiryCheck();
            cache.evictExpiredElements();
        } catch (Exception e) {
            log.error("evictExpiredLocalCache error!", e);
        }
    }

    /**
     * 2分钟清除一次 授权缓存
     */
    @Scheduled(cron = "0 */2 * * * ?")
    public void expiredTempCache() {
        try {
            CacheManager cacheManager = ehCacheManager.getCacheManager();
            assert cacheManager != null;
            Cache cache = cacheManager.getCache(EhCacheConstant.TEMP_CACHE_PREFIX);
            cache.getKeysWithExpiryCheck();
            cache.evictExpiredElements();
        } catch (Exception e) {
            log.error("evictExpiredLocalCache error!", e);
        }
    }

    /**
     * 4分钟清除一次 授权缓存
     */
    @Scheduled(cron = "0 */4 * * * ?")
    public void expiredUserDetailCache() {
        try {
            CacheManager cacheManager = ehCacheManager.getCacheManager();
            assert cacheManager != null;
            Cache cache = cacheManager.getCache(EhCacheConstant.USER_DETAILS);
            cache.getKeysWithExpiryCheck();
            cache.evictExpiredElements();
        } catch (Exception e) {
            log.error("evictExpiredLocalCache error!", e);
        }
    }

    /**
     * 30分钟清除一次 授权缓存
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void expiredConfigCache() {
        try {
            CacheManager cacheManager = ehCacheManager.getCacheManager();
            assert cacheManager != null;
            Cache cache = cacheManager.getCache(EhCacheConstant.CONFIG_CACHE_PREFIX);
            cache.getKeysWithExpiryCheck();
            cache.evictExpiredElements();
        } catch (Exception e) {
            log.error("evictExpiredLocalCache error!", e);
        }
    }
}
