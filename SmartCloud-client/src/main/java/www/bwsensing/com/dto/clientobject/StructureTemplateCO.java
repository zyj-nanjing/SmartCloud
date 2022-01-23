package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class StructureTemplateCO extends DTO {
    /**主键*/
    private Integer id;
    /**模板名称*/
    private String templateName;
    /**所属行业*/
    private List<IndustryFieldCO> belowField;
    /**预设部门*/
    private List<DeptTemplateCO> deptTemplates;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改者*/
    private String updater;
    /**修改时间*/
    private Date updateTime;
}
