package www.bwsensing.com.domain.device.model.data.model;

import lombok.Data;
import java.util.List;
import www.bwsensing.com.domain.monitor.model.MonitorItem;


/**
 * 计算模型
 * @author macos-zyj
 */
@Data
public class DataComputational {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 数据项编嘛
     */
    private String dataId;

    /**
     * 监测因素
     */
    private List<MonitorItem> protoItems;

    /**
     * 计算公式
     */
    private String computationFormula;

}
