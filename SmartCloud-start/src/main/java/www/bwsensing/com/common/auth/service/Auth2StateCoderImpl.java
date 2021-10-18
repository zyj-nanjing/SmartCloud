package www.bwsensing.com.common.auth.service;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import top.dcenter.ums.security.core.oauth.service.Auth2StateCoder;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * 对 state 的加密解密, 进行传递参数
 * @author YongWu zheng
 * @version V2.0  Created by 2020/10/26 18:50
 */
@Component
public class Auth2StateCoderImpl implements Auth2StateCoder {

    @Override
    public String encode(@NonNull String state, @NonNull HttpServletRequest request) {
        final String referer = request.getHeader("Referer");
        if (StringUtils.hasText(referer)) {
            state = Base64Utils.encodeToUrlSafeString(referer.getBytes(StandardCharsets.UTF_8));
        }
        else {
            state = Base64Utils.encodeToUrlSafeString(state.getBytes(StandardCharsets.UTF_8));
        }
        // 其他混淆逻辑, 保证 state 安全
        return state;
    }

    @Override
    public String decode(@NonNull String encoderState) {
        // 其他解密混淆逻辑, 保证 state 安全
        return new String(Base64Utils.decodeFromUrlSafeString(encoderState), StandardCharsets.UTF_8);
    }
}
