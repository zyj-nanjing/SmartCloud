package www.bwsensing.com.monitor.gatewayimpl.database.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
/**
 * @author macos-zyj
 */
@Data
public class MonitorPositionModelDO {
    /**主键*/
    private Integer id;
    /**结构物标识编码*/
    @JsonIgnore
    private String structureCode;
    /**结构物模板编号*/
    @JsonIgnore
    private Integer modelId;
    /**位置名称**/
    private String name;
    /**图片*/
    private String picture;
    /**点位说明**/
    private String comment;
    /**版本**/
    private Double version;
    /**结构体版本*/
    private Double structureVersion;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**创建者*/
    private String creator;
    /***创建时间*/
    private Date createTime;
}
