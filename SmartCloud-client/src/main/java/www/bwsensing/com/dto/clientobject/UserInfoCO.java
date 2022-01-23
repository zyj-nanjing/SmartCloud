package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class UserInfoCO extends DTO {
    private Integer id;
    /**用户名**/
    private String userName;
    /**账户名称*/
    private String accountName;
    /**工号**/
    private String workNumber;
    /** 头像 */
    private String avatar;
    /**手机号**/
    private String mobile;
    /**邮件*/
    private String email;
    /** 职位 */
    private String position;
    /**分组编号**/
    private Integer groupId;
    /**操作组**/
    private String operateGroup;
    /**角色*/
    private String role;
    /**用户对应可访问的路由**/
    private List<String> roles;
    /***是否有效*/
    private boolean enabled;
    /**是否为管理员*/
    private boolean isAdmin;
    /**账户是否被锁**/
    private boolean accountNonLocked;
    /**是否允许通知*/
    private boolean enableNotification;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getOperateGroup() {
        return operateGroup;
    }

    public void setOperateGroup(String operateGroup) {
        this.operateGroup = operateGroup;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(Boolean enableNotification) {
        this.enableNotification = enableNotification;
    }
}
