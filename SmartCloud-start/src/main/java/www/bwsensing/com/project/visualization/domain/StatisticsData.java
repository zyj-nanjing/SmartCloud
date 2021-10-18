package www.bwsensing.com.project.visualization.domain;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @author macos-zyj
 */
@Data
public class StatisticsData implements  Comparable<StatisticsData>{

    private Timestamp ts;
    private Float value;
    private String data;

    @Override
    public int compareTo(StatisticsData data) {
        return this.getTs().compareTo(data.getTs());
    }
}
