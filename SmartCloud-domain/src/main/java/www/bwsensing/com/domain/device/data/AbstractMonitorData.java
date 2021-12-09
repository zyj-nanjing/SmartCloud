package www.bwsensing.com.domain.device.data;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 自定义抽象监测数据
 * @author macos-zyj
 */
@Data
public  abstract  class AbstractMonitorData {
    /**原始数据*/
    private String raw;
    /**时间戳**/
    private Date timestamp;
    /**是否为Hex码*/
    private boolean isHexDecode;
    /**
     * 数据集
     */
    private List<MonitorData> dataCollection;

}
