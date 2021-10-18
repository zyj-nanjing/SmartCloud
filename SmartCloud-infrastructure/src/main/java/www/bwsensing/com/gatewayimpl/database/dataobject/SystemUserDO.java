package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;
/**
 * @author macos-zyj
 */
@Data
public class SystemUserDO {
    private Integer id;
    private String workNumber;
    private String accountName;
    private String password;
    private String userName;
    private String avatar;
    private String mobile;
    private String email;
    private String position;
    /**权限编号*/
    private Integer roleId;
    private String role;
    private Integer operateGroupId;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Date lastLoginTime;
    private Boolean lastLease;
    private Date leaseStartTime;
    private Integer leaseTime;
}
