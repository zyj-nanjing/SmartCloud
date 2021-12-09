package www.bwsensing.com.gatewayimpl.database.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class ProjectMemberDO {
    /**主键*/
    private Integer id;
    /**项目编号*/
    @JsonIgnore
    private Integer projectId;
    /**角色编码*/
    private String roleCode;
    /**角色名称*/
    private String roleName;
    /**用户名称*/
    private String userName;
    /**用户编号*/
    private Integer userId;
    /**联系手机号*/
    private String phoneNumber;
    /**用户账户名称*/
    @JsonIgnore
    private String accountName;
    /**加入时间*/
    private Date joinTime;
}
