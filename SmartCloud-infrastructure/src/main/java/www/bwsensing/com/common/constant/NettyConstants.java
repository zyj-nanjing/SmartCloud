package www.bwsensing.com.common.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author macos-zyj
 */
@Component
public class NettyConstants implements InitializingBean {
    public static  Integer  HTTP_PORT;
    public static  Integer SERVER_READ_IDEL_TIME_OUT;
    public static  Integer SERVER_WRITE_IDEL_TIME_OUT;
    public static  Integer SERVER_ALL_IDEL_TIME_OUT;

    @Value("${system.netty.port}")
    private String port;
    @Value("${system.netty.read_timeout}")
    private String readTimeout;
    @Value("${system.netty.wait_timeout}")
    private String waitTimeout;
    @Value("${system.netty.all_timeout}")
    private String allTimeout;
    @Override
    public void afterPropertiesSet() throws Exception {
        NettyConstants.HTTP_PORT = Integer.parseInt(this.port);
        NettyConstants.SERVER_READ_IDEL_TIME_OUT = Integer.parseInt(readTimeout);
        NettyConstants.SERVER_WRITE_IDEL_TIME_OUT = Integer.parseInt(this.waitTimeout);
        NettyConstants.SERVER_ALL_IDEL_TIME_OUT = Integer.parseInt(this.allTimeout);
    }
}
