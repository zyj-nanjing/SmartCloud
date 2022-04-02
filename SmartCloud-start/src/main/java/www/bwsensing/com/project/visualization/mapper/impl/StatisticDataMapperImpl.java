package www.bwsensing.com.project.visualization.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import www.bwsensing.com.project.visualization.domain.MonitorQuery;
import www.bwsensing.com.project.visualization.domain.StatisticsData;
import www.bwsensing.com.common.tdengine.common.BaseMapper;
import www.bwsensing.com.project.visualization.mapper.StatisticsDataMapper;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class StatisticDataMapperImpl extends BaseMapper implements StatisticsDataMapper {

    @Override
    public List<StatisticsData> listStatisticsAvgData(MonitorQuery query) {
        String sql = "SELECT avg(data_value) value FROM  smart_cloud.data_[device]_[code] WHERE  ts > [startTime] AND ts < [endTime] INTERVAL([interval])";
        String checkSql = sql.replace("[device]",query.getCurrentDevice()).replace("[code]",query.getCode())
                .replace("[startTime]","\""+query.getStartTime()+"\"").replace("[endTime]","\""+query.getEndTime()+"\"").
                replace("[interval]",query.getInterval());
        return jdbcTemplate.query(checkSql,new BeanPropertyRowMapper<>(StatisticsData.class));
    }

    @Override
    public List<StatisticsData> listStatisticsSpreadData(MonitorQuery query) {
        String sql = "SELECT SPREAD(data_value) value ,FIRST(data_value),LAST(data_value) FROM  smart_cloud.data_[device]_[code] WHERE  ts > [startTime] AND ts < [endTime] INTERVAL([interval])";
        String checkSql = sql.replace("[device]",query.getCurrentDevice()).replace("[code]",query.getCode())
                .replace("[startTime]","\""+query.getStartTime()+"\"").replace("[endTime]","\""+query.getEndTime()+"\"").
                replace("[interval]",query.getInterval());
        return jdbcTemplate.query(checkSql,new BeanPropertyRowMapper<>(StatisticsData.class));
    }

    @Override
    public StatisticsData getLastStatisticsData(String uniqueCode, String dataId) {
        String sql = "select ts,data_value value from data_[device]_[code] order by ts desc  limit 1";
        String checkSql = sql.replace("[device]",uniqueCode).replace("[code]",dataId);
        return jdbcTemplate.queryForObject(checkSql, new BeanPropertyRowMapper<>(StatisticsData.class));
    }
}
