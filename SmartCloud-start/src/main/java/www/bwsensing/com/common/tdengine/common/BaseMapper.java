package www.bwsensing.com.common.tdengine.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import www.bwsensing.com.common.tdengine.datasource.TDengineSource;

/**
 * TDengin 通用模板加载
 * @author macos-zyj
 */
@Slf4j
public class BaseMapper {
    protected static JdbcTemplate jdbcTemplate;

    static{
        try {
            log.info("TDengin are loading，please waiting");
            jdbcTemplate = new JdbcTemplate(TDengineSource.getDataSource());
        } catch (Exception e) {
            log.error("TDengin loading  or linking  error");
        }
    }
}
