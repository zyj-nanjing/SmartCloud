package www.bwsensing.com.common.utils;

import www.bwsensing.com.common.auth.common.IGrantedAuthority;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
public class IGrantedAuthorityUtils {
    public static List<IGrantedAuthority> createAuthorityList(String... authorities) {
        List<IGrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.length);
        for (String authority : authorities) {
            grantedAuthorities.add(new IGrantedAuthority(authority));
        }
        return grantedAuthorities;
    }
}
