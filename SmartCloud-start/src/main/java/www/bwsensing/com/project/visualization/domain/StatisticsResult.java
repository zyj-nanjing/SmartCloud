package www.bwsensing.com.project.visualization.domain;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class StatisticsResult {
    /**
     * 名称
     */
    private String name;

    /**
     * 单位
     */
    private String unity;
    /**
     * 数据
     */
    private List<Double> data;

    /**
     * X 轴时间戳
     */
    private List<Timestamp> timestamp;
}
