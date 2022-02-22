package www.bwsensing.com.domain.system.model.organization;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */
public enum StructureTypeEnum {
    /**内部组织*/
    INNER_STRUCTURE(1,"内部组织"),
    /**客户组织*/
    CLIENT_STRUCTURE(2,"客户组织");
    private final Integer typeId;
    private final String remark;

    StructureTypeEnum(Integer typeId, String remark) {
        this.typeId = typeId;
        this.remark = remark;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getRemark() {
        return remark;
    }

    public  static StructureTypeEnum getStructureType(Integer typeId){
        for (StructureTypeEnum currentType:values()) {
            if (currentType.getTypeId().equals(typeId)){
                return currentType;
            }
        }
        throw new BizException("ENUM_NOT_FOUND","该组织结构类型未找到!");
    }
}
