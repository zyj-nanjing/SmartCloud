package www.bwsensing.com.common.core.lru;

import lombok.Data;
import java.util.Random;
import java.io.Serializable;

/**
 * Map 结构封装以及业务扩展
 * @author macos-zyj
 */
@Data
public class LruCache<K,V> implements Serializable {
    private static final Integer DEFAULT_CAPACITY = 10;
    private LinkedCacheMap<K,V> innerCache;
    private Integer capacity;
    private Long versionCode;

    public LruCache() {

    }

    public LruCache(Integer capacity) {
        this.capacity = capacity;
        this.innerCache = new LinkedCacheMap<>(capacity);
        resetCode();
    }

    /**
     * 需要手动赋值给内置CacheMap对象
     * @param capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
        this.innerCache.setCapacity(capacity);
    }

    public void resetCode() {
        long timeMillis = System.currentTimeMillis();
        this.versionCode =  Math.abs(timeMillis-new Random().nextLong());
    }

    public  V get(Object key) {
        return innerCache.get(key);
    }


    public V put(K key, V value) {
        return innerCache.put(key, value);
    }


    public void remove(K key){
        innerCache.remove(key);
    }

    /**
     * 涉及到可能存在并发的场景则需要调用该方法进行版本Code的改变
     * @param currentCode
     * @return
     */
    public boolean isEquals(Long currentCode) {
        if (null == currentCode ||0 == currentCode.compareTo(versionCode)){
            resetCode();
            return true;
        }
        return false;
    }
}
