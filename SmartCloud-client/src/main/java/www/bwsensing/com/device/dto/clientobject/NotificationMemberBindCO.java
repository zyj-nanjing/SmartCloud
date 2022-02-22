package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class NotificationMemberBindCO extends DTO {
    /**用户名称*/
    private String userName;
    /**用户编号**/
    private Integer userId;
    /**选中状态*/
    private Boolean isSelect;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }
}
