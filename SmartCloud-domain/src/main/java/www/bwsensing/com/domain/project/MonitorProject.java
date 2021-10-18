package www.bwsensing.com.domain.project;

import lombok.Data;
import java.util.Date;
import java.util.List;
import www.bwsensing.com.domain.system.SystemUser;
import www.bwsensing.com.domain.monitor.MonitorStructure;

/**
 * @author macos-zyj
 */
@Data
public class MonitorProject {
    /**主键*/
    private Integer id;
    /**项目名称*/
    private String name;
    /**描述*/
    private String comment;
    /**图片*/
    private String picture;
    /**拥有者*/
    private SystemUser owner;
    /**项目成员*/
    private List<ProjectMember> innerMembers;
    /**所属结构物**/
    private List<MonitorStructure> structureList;
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;

    public void create(){
        this.creator = owner.getAccountName();
        this.createTime = new Date();
    }
}
