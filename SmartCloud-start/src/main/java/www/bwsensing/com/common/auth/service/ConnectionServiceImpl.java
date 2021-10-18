package www.bwsensing.com.common.auth.service;

import me.zhyd.oauth.model.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import top.dcenter.ums.security.core.oauth.entity.ConnectionData;
import top.dcenter.ums.security.core.oauth.entity.ConnectionDto;
import top.dcenter.ums.security.core.oauth.exception.RegisterUserFailureException;
import top.dcenter.ums.security.core.oauth.repository.exception.UpdateConnectionException;
import top.dcenter.ums.security.core.oauth.signup.ConnectionService;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class ConnectionServiceImpl implements ConnectionService {
    @Override
    public UserDetails signUp(AuthUser authUser, String providerId, String encodeState) throws RegisterUserFailureException {
        return null;
    }

    @Override
    public void updateUserConnectionAndAuthToken(AuthUser authUser, ConnectionData connectionData) throws UpdateConnectionException {

    }

    @Override
    public void binding(UserDetails principal, AuthUser authUser, String providerId) {

    }

    @Override
    public void unbinding(String userId, String providerId, String providerUserId) {

    }

    @Override
    public List<ConnectionData> findConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId) {
        return null;
    }

    @Override
    public MultiValueMap<String, ConnectionDto> listAllConnections(String userId) {
        return null;
    }
}
