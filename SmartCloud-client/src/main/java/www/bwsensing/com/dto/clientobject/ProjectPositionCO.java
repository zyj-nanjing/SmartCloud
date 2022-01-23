package www.bwsensing.com.dto.clientobject;


/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProjectPositionCO {
    private Integer id;
    /**测点名*/
    private String positionName;
    /**说明*/
    private String comment;
    /**结构物类型*/
    private String structureKind;
    /**结构物名称*/
    private String structureName;
    /**结构物版本*/
    private Double structureVersion;
    /**传感器名称**/
    private Integer sensorId;
    /**绑定状态**/
    private  Boolean bindStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public Double getStructureVersion() {
        return structureVersion;
    }

    public void setStructureVersion(Double structureVersion) {
        this.structureVersion = structureVersion;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Boolean getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Boolean bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getStructureKind() {
        return structureKind;
    }

    public void setStructureKind(String structureKind) {
        this.structureKind = structureKind;
    }
}
