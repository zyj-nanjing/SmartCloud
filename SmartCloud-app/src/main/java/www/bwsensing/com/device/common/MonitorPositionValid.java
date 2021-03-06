package www.bwsensing.com.device.common;

import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.domain.monitor.model.model.MonitorPositionModel;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author macos-zyj
 */
public class MonitorPositionValid {
    public static void validatePosition(List<MonitorPositionModel> positionList){
        if(checkPositionNameRepeat(positionList)){
            throw new BizException("POSITION_NOT_REPEAT","测点名称不能重复");
        }
    }
    /**
     * 判断List<MonitorPositionModel>的对象code是否有重复，有重复true
     *
     * @param positionList
     * @return
     */
    private static Boolean checkPositionNameRepeat(List<MonitorPositionModel> positionList) {
        Set<MonitorPositionModel> set = new TreeSet<>(Comparator.comparing(MonitorPositionModel::getName));
        set.addAll(positionList);
        return set.size() < positionList.size();
    }
}
