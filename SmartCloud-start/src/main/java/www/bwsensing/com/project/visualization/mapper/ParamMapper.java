package www.bwsensing.com.project.visualization.mapper;

import www.bwsensing.com.project.visualization.domain.Parameter;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ParamMapper {
    /**
     * 根据传感器型号进行数据查询
     * @param modelNo
     * @return
     */
    List<Parameter> selectParamByModelNo(String modelNo);
}
