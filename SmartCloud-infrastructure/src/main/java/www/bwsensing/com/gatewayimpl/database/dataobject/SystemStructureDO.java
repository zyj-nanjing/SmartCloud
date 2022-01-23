package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统组织对象 sys_structure
 *
 * @author mac_zyj
 * @date 2022-01-13
 */
@Data
public class SystemStructureDO
{

    /** 主键 */
    private Integer id;
    /** 组织名称 */
    private String name;
    /** 所属编号 */
    private Integer ownerId;
    /** 类型编号 */
    private Integer typeId;
    /**部门列表*/
    private List<SystemDeptDO> departments;
    /** 创建人 */
    private String creator;
    /** 创建时间 */
    private Date createTime;
    /** 修改人 */
    private String updater;
    /** 修改时间 */
    private Date  updateTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("ownerId", getOwnerId())
                .append("typeId", getTypeId())
                .append("departments",getDepartments())
                .append("creator", getCreator())
                .append("createTime", getCreateTime())
                .append("updater", getUpdater())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}