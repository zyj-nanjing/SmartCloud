package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class SensorBindCO extends DTO {
    /**传感器名称*/
    private String sensorName;
    /**传感器编号**/
    private Integer sensorId;
    /**选中状态*/
    private Boolean isSelect;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }
}
