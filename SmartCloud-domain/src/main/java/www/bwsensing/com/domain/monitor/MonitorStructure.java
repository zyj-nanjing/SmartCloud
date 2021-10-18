package www.bwsensing.com.domain.monitor;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import www.bwsensing.com.domain.device.SensorInfo;
import www.bwsensing.com.domain.monitor.model.MonitorPositionModel;
import www.bwsensing.com.domain.monitor.model.MonitorStructureModel;

/**
 * 结构物实体
 * @author macos-zyj
 */
@Data
public class MonitorStructure {
    /**主键*/
    private Integer id;
    /**模型编号**/
    private Integer modelId;
    /**项目编号*/
    private Integer projectId;
    /**名称*/
    private String name;
    /**图片*/
    private String picture;
    /**结构物名称*/
    private String structureName;
    /**描述*/
    private String comment;
    /**传感器标位*/
    private List<MonitorPosition> positions;
    /**当前结构物版本**/
    private Double currentVersion;
    /**线上版本*/
    private Double onlineVersion;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改人*/
    private String updater;
    /**修改时间*/
    private Date updateTime;

    public void  updateStructureVersion(MonitorStructureModel structureModel,List<SensorInfo> sensorDevices){
        if(!structureModel.getVersion().equals(this.getCurrentVersion())){
            this.modelId = structureModel.getId();
            this.name = structureModel.getName();
            this.comment = structureModel.getComment();
            this.currentVersion = structureModel.getVersion();
            updatePosition(structureModel.getPositionList(),sensorDevices,this.getId());
        }
    }

    private void updatePosition(List<MonitorPositionModel> positionModels, List<SensorInfo> sensorDevices, int structureId){
        List<MonitorPosition> editPositions = new ArrayList<>();
        positionModels.forEach(
                position ->{
                    MonitorPosition editPosition = getPositionByName(position.getName());
                    if(null == editPosition){
                        editPositions.add(position.initMonitorPosition(structureId));
                    } else{
                        editPosition.setName(position.getName());
                        editPosition.setComment(position.getComment());
                        editPosition.setOrderSort(position.getOrderSort());
                        editPositions.add(editPosition);
                    }
                }
        );
        sensorDevices.forEach(sensor->{
            if (editPositions.stream().noneMatch(position ->sensor.getPositionId().equals(position.getId()))){
                sensor.setProjectId(-1);
                sensor.setPositionId(-1);
            }
        });
        positions.clear();
        positions.addAll(editPositions);
    }

    private MonitorPosition getPositionByName(String positionName){
        for(MonitorPosition position : positions){
            if(position.getName().equals(positionName)){
                return position;
            }
        }
        return null;
    }
}
