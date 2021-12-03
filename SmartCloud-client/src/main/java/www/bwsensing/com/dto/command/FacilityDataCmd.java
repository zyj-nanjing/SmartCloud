package www.bwsensing.com.dto.command;

import lombok.Data;
import java.util.List;
import java.io.Serializable;
import com.alibaba.cola.extension.BizScenario;

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
     * 用例场景
     */
    private BizScenario bizScenario;
}