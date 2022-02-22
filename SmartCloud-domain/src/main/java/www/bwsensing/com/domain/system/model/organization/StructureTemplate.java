package www.bwsensing.com.domain.system.model.organization;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import www.bwsensing.com.domain.monitor.model.industry.IndustryField;
/**
 * @author macos-zyj
 */
@Data
public class StructureTemplate {
    /**主键*/
    private Integer id;
    /**模板名称*/
    private String templateName;
    /**所属行业*/
    private List<IndustryField> belowField;
    /**预设部门*/
    private List<SystemDeptTemplate> deptTemplates;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改者*/
    private String updater;
    /**修改时间*/
    private Date updateTime;

    public SystemStructure initializeStructure(String name){
        SystemStructure result = new SystemStructure(name);
        if( null != this.getDeptTemplates()){
            List<SystemDept> deptCollection = new ArrayList<>(this.getDeptTemplates().size());
            this.getDeptTemplates().forEach(deptTemplate-> deptCollection.add(deptTemplate.initSystemDept()));
            result.setDepartments(deptCollection);
        }
        return result;
    }
}
