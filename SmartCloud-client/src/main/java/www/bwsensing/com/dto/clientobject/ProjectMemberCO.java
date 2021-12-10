package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProjectMemberCO extends DTO {
    private Integer id;
    /**角色编码*/
    private String roleCode;
    /**角色名称*/
    private String roleName;
    /**用户名称*/
    private String userName;
    /**联系手机号*/
    private String phoneNumber;
    /**用户账户名称*/
    private String accountName;
    /**用户编号*/
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
