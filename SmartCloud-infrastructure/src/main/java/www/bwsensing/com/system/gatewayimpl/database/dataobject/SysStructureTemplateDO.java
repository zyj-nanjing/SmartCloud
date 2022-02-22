package www.bwsensing.com.system.gatewayimpl.database.dataobject;

import lombok.Data;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.IndustryFieldDO;

import java.util.Date;
import java.util.List;
/**
 * 系统组织模板对象 sys_structure_template
 * @author mac_zyj
 * @date 2022-01-13
 */
@Data
public class SysStructureTemplateDO
{
    /** 主键 */
    private Integer id;
    /** 模板名称 */
    private String templateName;
    /**行业集合*/
    private List<IndustryFieldDO> belowField;
    /**部门列表*/
    private List<SysDeptTemplateDO> deptTemplates;
    /** 创建人 */
    private String creator;
    /** 创建时间 */
    private Date createTime;
    /** 修改人 */
    private String updater;
    /** 修改时间 */
    private Date  updateTime;
}