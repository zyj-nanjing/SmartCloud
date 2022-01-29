package www.bwsensing.com.common.core.lru;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * 注意千万不能在里面直接分配版本号 否则每次序列化的时候都会改变版本号
 * 需要在外部进行版本号分配
 * @author macos-zyj
 */
public class LruLinkedCacheMap<K,V> extends LinkedHashMap<K,V> implements Serializable {
    private static final long serialVersionUID = 25465L;
    private static final Integer DEFAULT_CAPACITY = 10;
    /**
     * 定一缓存容量
     */
    private int capacity;

    public LruLinkedCacheMap() {
        this(DEFAULT_CAPACITY);
        this.capacity = DEFAULT_CAPACITY;
    }

    public LruLinkedCacheMap(int capacity){
        // AccessOrder = true
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }
    /**
     * 实现LRU的关键方法，如果 map 里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
     * @param eldest
     * @return
     */
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest){
        return size()>capacity;
    }

    @Override
    public  V get(Object key) {
        return super.get(key);
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    public long getUuidVersionCode(){
        long timeMillis = System.currentTimeMillis();
        return Math.abs(timeMillis-new Random().nextLong());
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

