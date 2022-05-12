package www.bwsensing.com.domain.device.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */
public enum ComputationHandleKind {
    /**
     * 最新数据
     */
    LATEST_DATA(1,"取触发情况下的最新数据!"),
    /**
     * 间隔数据
     */
    INTERVAL_DATA(2,"取两次触发间的数据");
    private final Integer value;

    private final String description;

    ComputationHandleKind(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
    public static ComputationHandleKind getComputationHandleKind(Integer value){
        for (ComputationHandleKind current:values()){
            if (current.getValue().equals(value)) {
                return current;
            }
        }
        throw new BizException("computation_handle_kind_not_found","设备属性类型不存在");
    }
}
