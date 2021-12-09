package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class MonitorStructureModelDO {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**结构物模板图片*/
    private String picture;
    /**结构物标识编码*/
    private String structureCode;
    /**描述*/
    private String comment;
    /**是否为公有结构物*/
    private Boolean isPublic;
    /**是否包含手机号*/
    private Boolean isContainMobile;
    /**是否包含经纬度*/
    private Boolean isContainPosition;
    /**版本**/
    private Double version;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**小组编号*/
    private Integer groupId;
    /**测点模板合集**/
    private List<MonitorPositionModelDO> positionList;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改者*/
    private String editor;
    /**修改时间*/
    private Date editTime;
}
