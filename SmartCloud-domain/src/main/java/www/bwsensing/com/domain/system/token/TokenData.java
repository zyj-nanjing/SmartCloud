package www.bwsensing.com.domain.system.token;

import lombok.Data;
/**
 * @author macos-zyj
 */
@Data
public class TokenData  {
    /**账户名称*/
    private String accountName;
    /**用户名**/
    private String userName;
    /**角色*/
    private String role;
    /**用户编号*/
    private Integer userId;
    /**小组编号*/
    private Integer groupId;
}
