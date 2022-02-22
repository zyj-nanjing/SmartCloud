package www.bwsensing.com.project.gatewayimpl.database.dataobject;

import lombok.Data;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorStructureDO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class MonitorProjectDO {
    /**主键*/
    private Integer id;
    /**项目名称*/
    private String name;
    /**拥有者*/
    private Integer ownerId;
    /**图片*/
    private String picture;
    /**描述*/
    private String comment;
    /**项目成员*/
    private List<ProjectMemberDO> memberArray;
    /**所属结构物**/
    private List<MonitorStructureDO> structureArray;
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;
}
