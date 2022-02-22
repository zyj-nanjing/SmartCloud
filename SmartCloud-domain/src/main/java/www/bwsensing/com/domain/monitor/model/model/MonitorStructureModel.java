package www.bwsensing.com.domain.monitor.model.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import www.bwsensing.com.domain.common.UuidUtils;
import www.bwsensing.com.domain.monitor.model.industry.IndustryField;
import www.bwsensing.com.domain.monitor.model.MonitorPosition;
import www.bwsensing.com.domain.monitor.model.MonitorStructure;

/**
 * @author macos-zyj
 */
@Data
public class MonitorStructureModel {
    private static final double ADD_LENGTH = 0.01;
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**结构物标识编码*/
    private String structureCode;
    /**图片*/
    private String picture;
    /**描述*/
    private String comment;
    /**传感器模板标位*/
    private List<MonitorPositionModel> positionList;
    /**行业领域 公共结构物需要对对应的行业进行绑定 非公共结构物 自动继承企业小组的行业领域*/
    private IndustryField industryField;
    /**是否为公有结构物*/
    private Boolean isPublic;
    private Boolean isContainMobile;
    private Boolean isContainPosition;
    /**版本**/
    private Double version;
    /**小组编号*/
    private Integer groupId;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改者*/
    private String editor;
    /**修改时间*/
    private Date editTime;

    public MonitorStructure initMonitorStructure(String name,String creator,int modelId){
        MonitorStructure monitorStructure = new MonitorStructure();
        monitorStructure.setName(name);
        monitorStructure.setStructureName(this.getName());
        monitorStructure.setModelId(modelId);
        monitorStructure.setComment(this.getComment());
        monitorStructure.setCurrentVersion(this.getVersion());
        monitorStructure.setOrderSort(this.getOrderSort());
        monitorStructure.setEffective(this.getEffective());
        monitorStructure.setCreator(creator);
        if( null != positionList){
            List<MonitorPosition> positions = new ArrayList<>(positionList.size());
            positionList.forEach(positionModel-> positions.add(positionModel.initMonitorPosition()));
            monitorStructure.setPositions(positions);
        }
        return monitorStructure;
    }

    public void saveOrUpdate(String editor){
        if (null == this.version ){
            this.version = ADD_LENGTH;
            this.creator = editor;
            this.createTime = new Date();
            this.structureCode = UuidUtils.generateShortUuid();
        } else{
            this.version += ADD_LENGTH;
            this.creator = editor;
            this.createTime = new Date();
        }
        if(null == this.isPublic){
            this.isPublic = false;
        }
        if(null == this.isContainMobile){
            this.isContainMobile = false;
        }
        if(null == this.isContainPosition){
            this.isContainPosition = false;
        }
    }

}
