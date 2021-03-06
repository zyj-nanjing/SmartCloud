package www.bwsensing.com.domain.system.model.organization;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 系统组织
 * @author macos-zyj
 */
@Data
public class SystemStructure {
    /**主键*/
    private Integer id;
    /**编码*/
    private String code;
    /**名称*/
    private String name;
    /**所属编号*/
    private Integer ownerId;
    /**是否允许邀请加入(预留)*/
    private Boolean allowInvitation;
    /**邀请码(预留)*/
    private String invitationCode;
    /**组织结构类型*/
    private StructureTypeEnum structureType;
    /**下属部门*/
    private List<SystemDept> departments;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改者*/
    private String updater;
    /**修改时间*/
    private Date updateTime;

    public SystemStructure() {
    }

    public SystemStructure(String name) {
        this.name = name;
    }
}
