package www.bwsensing.com.project.visualization.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import www.bwsensing.com.project.visualization.domain.DeviceDataQuery;
import www.bwsensing.com.project.visualization.domain.StatisticsData;
import www.bwsensing.com.common.tdengine.common.BaseMapper;
import www.bwsensing.com.project.visualization.domain.StatisticsQuery;
import www.bwsensing.com.project.visualization.mapper.StatisticsDataMapper;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class StatisticDataMapperImpl extends BaseMapper implements StatisticsDataMapper {

    @Override
    public List<StatisticsData> listStatisticsAvgData(DeviceDataQuery query) {
        String sql = "SELECT avg(data_value) value FROM  data_[device]_[code] WHERE  ts > [startTime] AND ts < [endTime] INTERVAL([interval])";
        String checkSql = sql.replace("[device]",query.getCurrentDevice()).replace("[code]",query.getCode())
                .replace("[startTime]","\""+query.getStartTime()+"\"").replace("[endTime]","\""+query.getEndTime()+"\"").
                replace("[interval]",query.getInterval());
        return jdbcTemplate.query(checkSql,new BeanPropertyRowMapper<>(StatisticsData.class));
    }

    @Override
    public List<StatisticsData> listStatisticsSpreadData(DeviceDataQuery query) {
        String sql = "SELECT SPREAD(data_value) value ,FIRST(data_value),LAST(data_value) FROM  data_[device]_[code] WHERE  ts > [startTime] AND ts < [endTime] INTERVAL([interval])";
        String checkSql = sql.replace("[device]",query.getCurrentDevice()).replace("[code]",query.getCode())
                .replace("[startTime]","\""+query.getStartTime()+"\"").replace("[endTime]","\""+query.getEndTime()+"\"").
                replace("[interval]",query.getInterval());
        return jdbcTemplate.query(checkSql,new BeanPropertyRowMapper<>(StatisticsData.class));
    }

    @Override
    public List<StatisticsData> listStatisticsCollection(StatisticsQuery statisticsQuery) {
        String sql = "select ts,data_value value from data_[device]_[code]  WHERE  ts > [startTime] AND ts < [endTime] ";
        String checkSql = sql.replace("[device]",statisticsQuery.getUniqueCode()).replace("[code]",statisticsQuery.getDataId())
                .replace("[startTime]","\""+statisticsQuery.getStartTime()+"\"").replace("[endTime]","\""+statisticsQuery.getEndTime()+"\"");
        return jdbcTemplate.query(checkSql,new BeanPropertyRowMapper<>(StatisticsData.class));
    }

    @Override
    public StatisticsData getLastStatisticsData(String uniqueCode, String dataId) {
        String sql = "select ts,data_value value from data_[device]_[code] order by ts desc  limit 1";
        String checkSql = sql.replace("[device]",uniqueCode).replace("[code]",dataId);
        return jdbcTemplate.queryForObject(checkSql, new BeanPropertyRowMapper<>(StatisticsData.class));
    }
}
