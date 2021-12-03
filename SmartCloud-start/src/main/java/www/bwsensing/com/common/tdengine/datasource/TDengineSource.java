package www.bwsensing.com.common.tdengine.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * 时序数据库数据源
 * @author macos-zyj
 */
@Slf4j
@SuppressWarnings("all")
public class TDengineSource {

    private static DataSource localMysqlDataBase;

    private static void initDataSource( ) throws Exception {
        Properties pro = new Properties();
        InputStream is = TDengineSource.class.getClassLoader().getResourceAsStream("config/tdengin-druid.properties");
        pro.load(is);
        //4.获取连接池对象
        localMysqlDataBase = DruidDataSourceFactory.createDataSource(pro);
    }


    public  static DataSource getDataSource() throws Exception {
        initDataSource();
        return localMysqlDataBase;
    }
}
