package www.bwsensing.com.extensionpoint;

import com.alibaba.cola.extension.ExtensionPointI;
import java.util.Map;

/**
 * @author macos-zyj
 */
public interface LruCachePushExtPt extends ExtensionPointI {
    /**
     * 根据缓存的顺序 修改数据表 orderNumber
     * @param orderMap
     */
    void databaseUpdate(Map<Object,Integer> orderMap);
}
