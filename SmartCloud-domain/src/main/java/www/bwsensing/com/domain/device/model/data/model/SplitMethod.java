package www.bwsensing.com.domain.device.model.data.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */

public enum SplitMethod {
    /***
     * 通过分隔符进行分隔
     */
    BY_SEPARATOR(0,"分隔符","通过类似于,这样的分隔符进行数据截取"),
    /***
     * 通过数据项字长分隔
     */
    BY_DATA_LENGTH(1,"数据项字长截取","通过基础数据长度以及对应的数据项数据长度进行截取");
    /**
     * 类型
     */
    private Integer type;
    /**
     * 分隔类型名称
     */
    private String typeName;
    /**
     * 备注
     */
    private String remark;

    SplitMethod(Integer type, String typeName, String remark) {
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

    public static SplitMethod getSplitMethod(Integer typeId){
        for (SplitMethod current:values()){
            if (current.getType().equals(typeId)) {
                return current;
            }
        }
        throw new BizException("split_method_not_found","分隔方式不存在");
    }



}
