package www.bwsensing.com.device.dto.command;

import lombok.Data;
import java.util.List;
import java.sql.Timestamp;
import java.io.Serializable;
import com.alibaba.cola.extension.BizScenario;
import www.bwsensing.com.common.command.CommonCommand;

/**
 * @author macos-zyj
 */
@Data
public class FacilityDataCmd extends CommonCommand implements Serializable {

    private Integer modelWebId;
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
}
