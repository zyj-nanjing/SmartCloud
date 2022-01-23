package www.bwsensing.com.common.auth.common;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author macos-zyj
 */
@Data
public class IGrantedAuthority implements GrantedAuthority {
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public IGrantedAuthority() {
    }

    public IGrantedAuthority(String authority) {
        this.authority = authority;
    }
}
