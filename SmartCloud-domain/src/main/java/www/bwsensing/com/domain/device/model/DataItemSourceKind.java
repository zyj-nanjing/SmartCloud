package www.bwsensing.com.domain.device.model;
import com.alibaba.cola.exception.BizException;

/**
 * 检测项来源类型
 * @author macos-zyj
 */
public enum DataItemSourceKind {
    /**
     * 直接数据
     */
    DIRECT_DATA(1,"直接数据-从数据传参中直接获取"),
    /**
     * 计算数据
     */
    CALCULATION_DATA(2,"计算数据-从一系列的直接数据中获取"),
    /**
     * 接入数据
     */
    ACCESS_DATA(3,"接入数据-从别的平台拿取的数据");

    private final Integer type;
    private final String remark;

    DataItemSourceKind(Integer type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }
    public static DataItemSourceKind getItemSourceKind(Integer typeId){
        for (DataItemSourceKind current:values()){
            if (current.getType().equals(typeId)) {
                return current;
            }
        }
        throw new BizException("item_source_kind_not_found","数据来源类型不存在");
    }
}
