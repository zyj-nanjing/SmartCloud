package www.bwsensing.com.common.tdengine.common;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author macos-zyj
 */
@Component
public class TDengineConstants implements InitializingBean {
    public static  String HOST;
    public static Integer PORT;
    public static  String DATABASE;
    public static  String USER;
    public static  String PASSWORD;

    @Value("${tdengine.host}")
    private String host;
    @Value("${tdengine.port}")
    private String port;
    @Value("${tdengine.database}")
    private String database;
    @Value("${tdengine.user}")
    private String user;
    @Value("${tdengine.password}")
    private String password;
    @Override
    public void afterPropertiesSet() throws Exception {
        TDengineConstants.HOST = host;
        TDengineConstants.PORT = Integer.parseInt(port);
        TDengineConstants.DATABASE = database;
        TDengineConstants.USER = user;
        TDengineConstants.PASSWORD = password;
    }
}
