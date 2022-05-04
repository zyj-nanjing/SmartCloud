package www.bwsensing.com.domain.device.model.data.model;

import com.alibaba.cola.exception.BizException;
/**
 * @author macos-zyj
 */
public enum DataForm {
    /**
     * 数据格式
     */
    SINGLE_LINE_DATA(1,"单行单数据","单数据集为一行"),
    DOUBLE_LINE_DATA(2,"单行双数据","双数据集为一行"),
    JSON_DATA(3,"JSON体","数据位JSON格式");
    /**
     * 类型
     */
    private final Integer type;
    /**
     * 分隔类型名称
     */
    private final String typeName;
    /**
     * 备注
     */
    private final String remark;

    DataForm(Integer type, String typeName, String remark) {
        this.type = type;
        this.typeName = typeName;
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getRemark() {
        return remark;
    }


    public static DataForm getDataForm(Integer typeId){
        for (DataForm current:values()){
            if (current.getType().equals(typeId)) {
                return current;
            }
        }
        throw new BizException("data_format_not_found","数据格式不存在");
    }
}
