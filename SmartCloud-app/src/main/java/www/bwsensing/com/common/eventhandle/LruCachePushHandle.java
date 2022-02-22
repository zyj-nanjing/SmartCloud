package www.bwsensing.com.common.eventhandle;

import java.util.Map;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.domainevent.LruCachePushEvent;
import www.bwsensing.com.common.extensionpoint.LruCachePushExtPt;

/**
 * 获取对应的顺序权重 并直接往数据表上面的order_number 上面加
 * @author macos-zyj
 */
@CatchAndLog
@Slf4j
@EventHandler
public class LruCachePushHandle implements EventHandlerI<Response, LruCachePushEvent> {
    @Resource
    private ExtensionExecutor extensionExecutor;

    @Override
    public Response execute(LruCachePushEvent cachePushEvent) {
        Map<Object,Integer> orderMap = getOrderMapFromCacheMap(cachePushEvent.getCache());
        extensionExecutor.executeVoid(LruCachePushExtPt.class, initScenario(cachePushEvent.getBizId()),
                extension -> extension.databaseUpdate(orderMap));
        return Response.buildSuccess();
    }

    private BizScenario initScenario(String bizId){
        return BizScenario.valueOf(BizScenarioCode.BIZ_ID_CLOUD, bizId,BizScenarioCode.UPDATE_DATA_ORDER);
    }

    private<K,V> Map<Object,Integer> getOrderMapFromCacheMap(HashMap<K,V> cacheMap){
        Map<Object, Integer> orderMap = new HashMap<>(cacheMap.size());
        int weights = 1;
        for (Map.Entry<K, V> kvEntry : cacheMap.entrySet()) {
            orderMap.put(kvEntry.getKey(),weights);
            weights++;
        }
        return orderMap;
    }
}
