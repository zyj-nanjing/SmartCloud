package www.bwsensing.com.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author macos-zyj
 */
@Component
@ConfigurationProperties(prefix = "smartcloud")
public class SmartCloudConfig {
    /** 项目名称 */
    private static String name;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String copyrightYear;



    public static String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        SmartCloudConfig.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        SmartCloudConfig.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        SmartCloudConfig.copyrightYear = copyrightYear;
    }

}
