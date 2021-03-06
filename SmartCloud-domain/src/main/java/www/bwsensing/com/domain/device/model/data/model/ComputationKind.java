package www.bwsensing.com.domain.device.model.data.model;

import com.alibaba.cola.exception.BizException;

/**
 * 计算形式
 * @author macos-zyj
 */
public enum ComputationKind {
    /**
     * 数据接收触发形式
     */
    SUBMIT_CALCULATION(1,"数据接收触发形式"),
    /**
     * 定时触发形式
     * 定时计算或连续查询
     */
    SCHEDULED_CALCULATION(2,"定时触发形式");

    /**
     * 计算类型
     */
    private final Integer type;
    /**
     * 备注
     */
    private final String remark;

    ComputationKind(Integer type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }
    public static ComputationKind getComputationKind(Integer typeId){
        for (ComputationKind current:values()){
            if (current.getType().equals(typeId)) {
                return current;
            }
        }
        throw new BizException("computation_kind_not_found","分隔方式不存在");
    }
}
