package www.bwsensing.com.device.dto.clientobject;

import www.bwsensing.com.common.clientobject.TimeSeriesDataCO;
import www.bwsensing.com.common.clientobject.DataItemsCO;
import com.alibaba.cola.dto.DTO;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class SensorApiCO extends DTO {

    /**
     * 设备唯一编码
     */
    private String uniqueCode;

    /**
     * 传感器名称
     */
    private String name;

    /**
     * 设备型号
     */
    private String modelName;

    /**
     * 传感器对应的数据项
     */
    private List<DataItemsCO> dataItems;

    /**
     * 当前数据集
     */
    private Map<String, TimeSeriesDataCO> dataMap;


    public SensorApiCO() {
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<DataItemsCO> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<DataItemsCO> dataItems) {
        this.dataItems = dataItems;
    }

    public Map<String, TimeSeriesDataCO> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, TimeSeriesDataCO> dataMap) {
        this.dataMap = dataMap;
    }
}
