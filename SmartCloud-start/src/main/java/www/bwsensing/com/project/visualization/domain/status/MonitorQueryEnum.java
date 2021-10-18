package www.bwsensing.com.project.visualization.domain.status;

import com.alibaba.cola.exception.BizException;

/**
 * 监测类型查询
 * @author macos-zyj
 */
public enum MonitorQueryEnum {
    /**平均*/
    AVG("avg","平均"),
    /**极值*/
    SPREAD("spread","极值");
    /**类型编号*/
    private String typeId;
    /**类型名称*/
    private String typeName;

    MonitorQueryEnum(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public static MonitorQueryEnum queryResult(String typeId){
        for (MonitorQueryEnum queryEnum:values()){
            if (queryEnum.getTypeId().equals(typeId)){
                return queryEnum;
            }
        }
        throw new BizException("QUERY_ID_NOT_FOUNT","查询编号不存在");
    }
}
