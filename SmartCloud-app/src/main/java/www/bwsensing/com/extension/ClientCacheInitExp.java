package www.bwsensing.com.extension;

import www.bwsensing.com.gatewayimpl.database.SystemClientMapper;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.extensionpoint.LruCachePushExtPt;
import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 暂定方案 存一个本地变量表 若发生变换则 保存不变则不进行加权
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,useCase = BizScenarioCode.SYSTEM_CLIENT,scenario = BizScenarioCode.UPDATE_DATA_ORDER)
public class ClientCacheInitExp implements LruCachePushExtPt {
    private static final Integer INCREASE_WEIGHT =1;
    private volatile Map<Object, Integer> lastOrderMap = new HashMap<>();

    @Resource
    private SystemClientMapper systemClientMapper;


    @Override
    public void databaseUpdate(Map<Object, Integer> orderMap) {
        if(!compareMap(orderMap)){
            lastOrderMap.clear();
            lastOrderMap.putAll(orderMap);
            int maxValue = 0;
            String maxKey = "";
            systemClientMapper.clearClientOrder();
            for (Map.Entry<Object, Integer> entryLocal : orderMap.entrySet()) {
                if(entryLocal.getValue() > maxValue){
                    maxValue = entryLocal.getValue();
                    maxKey = entryLocal.getKey().toString();
                }
                systemClientMapper.updateClientOrder(entryLocal.getValue(),Integer.valueOf(entryLocal.getKey().toString()));
            }
            systemClientMapper.addClientWeight(INCREASE_WEIGHT,Integer.valueOf(maxKey));
        }

    }

    private  boolean compareMap(Map<Object, Integer> currentMap){
        for (Map.Entry<Object, Integer> entryLocal : currentMap.entrySet()) {
            Integer currentValue = entryLocal.getValue() == null ? -1 : entryLocal.getValue();
            Integer localValue = lastOrderMap.get(entryLocal.getKey()) == null ? -1 : lastOrderMap.get(entryLocal.getKey());
            if (!localValue.equals(currentValue)) {
                return false;
            }
        }
        return true;
    }
}
