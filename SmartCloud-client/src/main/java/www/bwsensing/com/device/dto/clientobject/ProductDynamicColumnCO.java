package www.bwsensing.com.device.dto.clientobject;

import java.util.Map;
import com.alibaba.cola.dto.DTO;
import www.bwsensing.com.common.clientobject.TimeSeriesDataCO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductDynamicColumnCO extends DTO {
    /**
     * 当前数据集
     */
    private Map<String, TimeSeriesDataCO> dataMap;

    public ProductDynamicColumnCO() {

    }

    public Map<String, TimeSeriesDataCO> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, TimeSeriesDataCO> dataMap) {
        this.dataMap = dataMap;
    }
}
