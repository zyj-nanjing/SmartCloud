package www.bwsensing.com.common.clientobject;

import com.alibaba.cola.dto.DTO;
import java.sql.Timestamp;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class TimeSeriesDataCO extends DTO {
    /**
     * 时间戳
     */
    private Timestamp ts;
    /**
     * 数值
     */
    private Float dataValue;

    public TimeSeriesDataCO() {
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public Float getDataValue() {
        return dataValue;
    }

    public void setDataValue(Float dataValue) {
        this.dataValue = dataValue;
    }
}
