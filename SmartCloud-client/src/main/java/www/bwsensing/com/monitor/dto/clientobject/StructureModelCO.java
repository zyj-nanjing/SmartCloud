package www.bwsensing.com.monitor.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import www.bwsensing.com.monitor.dto.clientobject.PositionModelCO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class StructureModelCO extends DTO {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**图片*/
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
    /**测点模板合集**/
    private List<PositionModelCO> positionList;
    /**创建人*/
    private String creator;
    /**创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
