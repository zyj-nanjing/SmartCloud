package www.bwsensing.com.common.config;

import com.alibaba.cola.exception.SysException;
import io.jsonwebtoken.lang.Assert;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import java.io.InputStream;
import java.util.Properties;

/**
 * 时序数据库告警API配置
 * @author macos-zyj
 */
@Slf4j
@SuppressWarnings("all")
public enum AlertApiConfig {
    ADD_OR_EDIT("", HttpMethod.POST),
    DELETE("",HttpMethod.DELETE),
    RESUME("",HttpMethod.POST),
    LIST_RULE("",HttpMethod.GET);
    static{
        try{
            Properties pro = new Properties();
            InputStream is = AlertApiConfig.class.getClassLoader().getResourceAsStream("config/tdengin-alert.properties");
            pro.load(is);
            ADD_OR_EDIT.setUrl(pro.getProperty("add-edit"));
            DELETE.setUrl(pro.getProperty("delete"));
            RESUME.setUrl(pro.getProperty("resume-suspend"));
            LIST_RULE.setUrl(pro.getProperty("list-rule"));
            log.info("tdengin alert model api loading success");
        } catch (Exception e){
            log.warn("tdengin alert model api loading error info:{}",e.getLocalizedMessage());
            throw new SysException("TDENGIN_API_INIT_ERROR","Tdengine 预警Api加载有误");
        }
    }
    private String url;
    private HttpMethod method;

    AlertApiConfig(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        Assert.notNull(url,"ALert url not null");
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

}
