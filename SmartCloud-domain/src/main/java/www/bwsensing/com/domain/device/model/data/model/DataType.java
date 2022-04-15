package www.bwsensing.com.domain.device.model.data.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */
public enum DataType {
    /**
     * 数据类型
     */
    STRING("String"),
    FLOAT("Float"),
    DOUBLE("Double"),
    INT("Int");

    private final String type;

    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static DataType getDataType(String dataType){
        for (DataType  currentType : values()){
            if (currentType.getType().equals(dataType)){
                return currentType;
            }
        }
        throw new BizException("data_type_not_found","数据类型不存在");
    }
}
