package www.bwsensing.com.monitor.gatewayimpl.database.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorPositionDO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class MonitorStructureDO {
    /**主键*/
    private Integer id;
    /**项目编号*/
    @JsonIgnore
    private Integer projectId;
    /**模型编号**/
    @JsonIgnore
    private Integer modelId;
    /**名称*/
    private String name;
    /**结构物名称**/
    private String structureName;
    /**图片*/
    private String picture;
    /**描述*/
    private String comment;
    /**当前结构物版本**/
    private Double currentVersion;
    /**线上版本*/
    private Double onlineVersion;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**测点合集*/
    private List<MonitorPositionDO> positionList;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改人*/
    private String updater;
    /**修改时间*/
    private Date updateTime;
}
