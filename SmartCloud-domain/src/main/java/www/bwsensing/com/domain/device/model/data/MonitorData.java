package www.bwsensing.com.domain.device.model.data;

import lombok.Data;
import java.sql.Timestamp;


/**
 * TDengin 存储结构
 * @author macos-zyj
 */
@Data
public class MonitorData {
    /**
     * 分组编号
     */
    private Integer groupId;

    /**
     * 检测项
     */
    private String dataId;

    /**
     *数据值
     */
    private Double dataIdValue;

    /**
     *Sn编码
     */
    private String uniqueCode;

    /**
     *查询类型
     */
    private String type;

    /**
     *时间戳
     */
    private Timestamp timeStamp;
}
