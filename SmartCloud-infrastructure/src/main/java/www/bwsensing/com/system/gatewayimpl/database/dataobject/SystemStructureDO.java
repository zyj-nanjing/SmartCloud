package www.bwsensing.com.system.gatewayimpl.database.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统组织对象 sys_structure
 *
 * @author mac_zyj
 * @date 2022-01-13
 */
@Data
public class SystemStructureDO implements Serializable
{
    /** 主键 */
    private Integer id;
    /** 组织编码 */
    private String code;
    /** 组织名称 */
    private String name;
    /** 所属编号 */
    private Integer ownerId;
    /**是否允许邀请加入*/
    private Boolean allowInvitation;
    /**邀请码*/
    private String invitationCode;
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
        String sb = "SystemStructureDO{" + "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", typeId=" + typeId +
                ", departments=" + departments +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updater='" + updater + '\'' +
                ", updateTime=" + updateTime +
                '}';
        return sb;
    }
}