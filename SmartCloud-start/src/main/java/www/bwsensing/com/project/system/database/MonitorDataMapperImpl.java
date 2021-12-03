package www.bwsensing.com.project.system.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.alibaba.cola.exception.BizException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.tdengine.common.BaseMapper;
import www.bwsensing.com.domain.device.data.MonitorData;
import www.bwsensing.com.gatewayimpl.tdengin.MonitorDataMapper;


/**
 * @author macos-zyj
 */
@Component
public class MonitorDataMapperImpl extends BaseMapper implements MonitorDataMapper {

    @Override
    public int createSuperTable() {
        return jdbcTemplate.update("create stable  if not exists smart_cloud.sensor_data(ts timestamp, data_value float) TAGS(sn nchar(64),group_id int,data_id nchar(64))");
    }

    @Override
    public int createNewDataTable(MonitorData monitorData) {
        String prefix = "create table if not exists smart_cloud.data_"+monitorData.getSn()+"_"+monitorData.getDataId() ;
        return jdbcTemplate.update(
                prefix +" using smart_cloud.sensor_data tags(?,?,?)",
                monitorData.getSn(), monitorData.getGroupId(),monitorData.getDataId()
        );
    }

    @Override
    public int insertMonitorData(MonitorData monitorData) {
        String prefix = "insert into smart_cloud.data_"+monitorData.getSn()+"_"+monitorData.getDataId();
        return jdbcTemplate.update(
                prefix +" (ts, data_value) VALUES(?,?)",
                monitorData.getTimeStamp(), monitorData.getDataIdValue()
        );
    }

    @Override
    public Integer queryMonitorDataSize(String sn) {
        String sql = "select count(*) from sensor_data where sn ="+sn;
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public int[] batchMonitorData(List<MonitorData> dataCollection) {
        if (dataCollection.size()>0){
            return jdbcTemplate.batchUpdate("insert into smart_cloud.data_?_? using smart_cloud.sensor_data tags (?,?,?)  VALUES(?,?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1,dataCollection.get(i).getSn());
                    ps.setString(2,dataCollection.get(i).getDataId());
                    ps.setString(3,dataCollection.get(i).getSn());
                    ps.setInt(4,dataCollection.get(i).getGroupId());
                    ps.setString(5,dataCollection.get(i).getDataId());
                    ps.setTimestamp(6, dataCollection.get(i).getTimeStamp());
                    ps.setFloat(7, dataCollection.get(i).getDataIdValue());
                }

                @Override
                public int getBatchSize() {
                    return dataCollection.size();
                }
            });
        }
        throw new BizException("RECEIVE_DATA_IS_EMPTY","接收传感器数据为空");
    }
}
