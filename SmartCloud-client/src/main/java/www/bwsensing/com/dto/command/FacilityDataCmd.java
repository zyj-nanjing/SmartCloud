package www.bwsensing.com.dto.command;

import com.alibaba.cola.extension.BizScenario;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class FacilityDataCmd extends CommonCommand implements Serializable {
    /**
     * 设备Sn编码
     */
    private String sn;
    /**
     * 分析种类
     */
    private String analyseKind;
    /**
     * 接收数据
     */
    private List<String> receiveData;
    /**
     * 时间戳
     */
    private List<Timestamp> dataTimestamp;
    /**
     * 用例场景
     */
    private BizScenario bizScenario;
}
