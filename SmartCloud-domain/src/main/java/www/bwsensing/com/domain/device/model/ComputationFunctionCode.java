package www.bwsensing.com.domain.device.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */

public enum ComputationFunctionCode {
    /**
     * 平均值
     */
    AVG(1,"取所有数据的平均值进行计算"),
    /**
     * 最大值
     */
    MAX(2,"取所有数据的最大值进行计算"),
    /**
     * 最小值
     */
    MIN(3,"取所有数据的最小值进行计算");
    private final Integer value;

    private final String description;

    ComputationFunctionCode(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
    public static ComputationFunctionCode getComputationFunctionCode(Integer value){
        for (ComputationFunctionCode current:values()){
            if (current.getValue().equals(value)) {
                return current;
            }
        }
        throw new BizException("computation_function_not_found","计算方法不存在");
    }
}
