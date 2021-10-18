package www.bwsensing.com.project.visualization.mapper.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import www.bwsensing.com.project.visualization.domain.Parameter;
import www.bwsensing.com.common.tdengine.common.BaseMapper;
import www.bwsensing.com.project.visualization.mapper.ParamMapper;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class ParamMapperImpl extends BaseMapper implements ParamMapper {
    @Override
    public List<Parameter> selectParamByModelNo(String modelNo) {
        String sql = "select * from smart_cloud.sensor_data_parameter where  model_no = "+modelNo;
        try{
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Parameter>(Parameter.class));
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
