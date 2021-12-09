package www.bwsensing.com.domain.project;

import com.alibaba.cola.exception.Assert;
import lombok.Data;
import www.bwsensing.com.domain.monitor.MonitorStructure;
import www.bwsensing.com.domain.system.SystemUser;

import java.util.Date;
import java.util.List;

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

    public MonitorProject() {
    }

    public MonitorProject(SystemUser owner) {
        this.owner = owner;
    }

    public void create(){
        Assert.notNull(owner,"项目拥有者不能为空");
        this.creator = owner.getAccountName();
        this.createTime = new Date();
    }
}
