package www.bwsensing.com.domain.device.model;

import com.alibaba.cola.exception.BizException;

/**
 * 检测项属性类型
 * @author macos-zyj
 */
public enum DataItemAttribute {
    /**
     * 通用配置
     */
    GENERAL_STATUS(1,"通用配置","常用于显示电量sim卡号等"),
    /**
     * 设备数据
     */
    DEVICE_DATA(2,"设备数据","常用于对应的设备数据");

    /**
     * 属性类型
     */
    private final Integer type;
    /**
     * 属性名称
     */
    private final String name;

    /**
     * 备注
     */
    private final String remark;

    DataItemAttribute(Integer type, String name, String remark) {
        this.type = type;
        this.name = name;
        this.remark = remark;
    }


    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public static DataItemAttribute getDataItemAttribute(Integer typeId){
        for (DataItemAttribute current:values()){
            if (current.getType().equals(typeId)) {
                return current;
            }
        }
        throw new BizException("item_attribute_not_found","设备属性类型不存在");
    }
}
