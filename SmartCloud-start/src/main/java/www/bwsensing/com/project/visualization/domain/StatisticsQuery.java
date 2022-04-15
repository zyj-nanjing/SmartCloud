package www.bwsensing.com.project.visualization.domain;

import java.sql.Timestamp;

/**
 * @author macos-zyj
 */
public class StatisticsQuery {
    private String uniqueCode;
    private String dataId;
    private Timestamp startTime;
    private Timestamp endTime;

    public StatisticsQuery() {

    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
