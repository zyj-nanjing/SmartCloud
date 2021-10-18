package www.bwsensing.com.project.visualization.domain;

import lombok.Data;
import java.sql.Timestamp;


/**
 * @author macos-zyj
 */
@Data
public class MonitorData {
    private Integer groupId;
    private String dataId;
    private Float dataIdValue;
    private String sn;
    private String type;
    private Timestamp timeStamp;
}
