package www.bwsensing.com.common.auth.service;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import www.bwsensing.com.common.auth.common.IGrantedAuthority;

import java.io.Serializable;
import java.util.*;

/**
 * @author macos-zyj
 */
@Data
public class SmartUser implements UserDetails {
    /**
     * 用户ID
     */
    private Integer id;

    private  String username;

    private  String password;
    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 手机号
     */
    private String phone;

    private Set<IGrantedAuthority> authorities;


    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    public SmartUser(){
        super();
    }

    public SmartUser(Integer id, String username, String password,
                     Integer deptId, String phone,
                     Collection<IGrantedAuthority> authorities,
                     boolean accountNonExpired,
                     boolean accountNonLocked,
                     boolean credentialsNonExpired,
                     boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.deptId = deptId;
        this.phone = phone;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    private static SortedSet<IGrantedAuthority> sortAuthorities(Collection<IGrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<IGrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
        for (IGrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set. If the authority is null, it is a custom authority and should
            // precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }

    }
}
