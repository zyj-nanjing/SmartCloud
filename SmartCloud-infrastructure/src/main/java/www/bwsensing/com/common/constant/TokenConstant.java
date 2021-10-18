package www.bwsensing.com.common.constant;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author macos-zyj
 */
@Component
public class TokenConstant implements InitializingBean {
    public static Long  TOKEN_EXIST_TIME;
    public static String TOKEN_HEADER;
    public static String TOKEN_PREFIX;

    @Value("${system.token.exist_time}")
    private String tokenExistTime;
    @Value("${system.token.head}")
    private String tokenHead;
    @Value("${system.token.prefix}")
    private String tokenPrefix;

    @Override
    public void afterPropertiesSet() throws Exception {
        TokenConstant.TOKEN_HEADER = this.tokenHead;
        TokenConstant.TOKEN_PREFIX = this.tokenPrefix;
        TokenConstant.TOKEN_EXIST_TIME = Long.parseLong(this.tokenExistTime);
    }
}
