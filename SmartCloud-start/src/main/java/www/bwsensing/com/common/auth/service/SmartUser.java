package www.bwsensing.com.common.auth.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

/**
 * @author macos-zyj
 */
public class SmartUser extends User {
    /**
     * 用户ID
     */
    @Getter
    private final Integer id;

    /**
     * 部门ID
     */
    @Getter
    private final Integer deptId;

    /**
     * 手机号
     */
    @Getter
    private final String phone;

    public SmartUser(Integer id, Integer deptId, String username, String password, String phone, boolean enabled,
                   boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                   Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
        this.phone = phone;
    }

}
