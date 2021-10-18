package www.bwsensing.com.domain.project;

import lombok.Data;
import java.util.Date;

/**
 * 项目权限
 * @author macos-zyj
 */
@Data
public class ProjectMember {
    /**主键*/
    private Integer id;
    /**项目编号*/
    private Integer projectId;
    /**角色编码*/
    private String roleCode;
    /**角色名称*/
    private String roleName;
    /**用户编号*/
    private Integer userId;
    /**用户账户名称*/
    private String accountName;
    /**项目角色*/
    private ProjectRoleEnum projectRole;
    /**加入时间*/
    private Date joinTime;

    public void  createMember(Integer projectId,String roleCode){
        this.setProjectId(projectId);
        this.projectRole = ProjectRoleEnum.getProjectRoleByCode(roleCode);
        this.setRoleCode(roleCode);
        this.roleName = projectRole.getRoleName();
        this.joinTime = new Date();
    }

    public void createOwner(Integer userId,String accountName){
        this.projectRole = ProjectRoleEnum.PROJECT_OWNER;
        this.roleCode = projectRole.getRoleCode();
        this.roleName = projectRole.getRoleName();
        this.joinTime = new Date();
        this.userId = userId;
        this.accountName =accountName;
    }
}
