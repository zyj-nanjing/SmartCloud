package www.bwsensing.com.system.dto.clientobject;

import lombok.Data;
import java.util.Date;
import java.util.List;
import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class SystemStructureCO extends DTO {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**所属编号*/
    private Integer ownerId;
    /**是否允许邀请加入*/
    private Boolean allowInvitation;
    /**邀请码*/
    private String invitationCode;
    /**组织结构类型*/
    private String structureType;
    /**下属部门*/
    private List<SystemDeptCO> departments;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改者*/
    private String updater;
    /**修改时间*/
    private Date updateTime;
}
