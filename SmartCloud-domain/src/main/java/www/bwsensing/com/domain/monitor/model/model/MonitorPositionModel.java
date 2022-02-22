package www.bwsensing.com.domain.monitor.model.model;

import lombok.Data;
import www.bwsensing.com.domain.monitor.model.MonitorPosition;
import java.util.Date;

/**
 * 测点模板
 * @author macos-zyj
 */
@Data
public class MonitorPositionModel {
    private static final double ADD_LENGTH = 0.01;
    /**主键*/
    private Integer id;
    /**结构物标识编码*/
    private String structureCode;
    private Integer modelId;
    /**位置名称**/
    private String name;
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

    public MonitorPosition initMonitorPosition(){
        return initMonitorPosition(null);
    }
    public MonitorPosition initMonitorPosition(Integer structureId){
        MonitorPosition monitorPosition = new MonitorPosition();
        monitorPosition.setName(this.getName());
        monitorPosition.setComment(this.getComment());
        monitorPosition.setOrderSort(this.getOrderSort());
        monitorPosition.setEffective(true);
        if ( null != structureId){
            monitorPosition.setStructureId(structureId);
        }
        return monitorPosition;
    }

    public void create(String creator){
        versionAdd();
        this.createTime = new Date();
        this.creator = creator;
    }
    public void update(){
        versionAdd();
    }


    private void versionAdd(){
        if (null == this.version){
            this.version = ADD_LENGTH;
        } else{
            this.version += ADD_LENGTH;
        }
    }
}
