package www.bwsensing.com.project.analyse.state;

import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.project.analyse.AngleSensorState;
import www.bwsensing.com.project.analyse.SensorBaseState;

/**
 * 传感器特殊类型
 * @author macos-zyj
 */
public enum SensorAnalyseState
{
    /**倾角传感器*/
    ANGLE_SENSOR_ANALYSE("ANGLE_SENSOR",new AngleSensorState());
    private final String typeName;
    private final SensorBaseState baseState;

    SensorAnalyseState(String typeName, SensorBaseState baseState) {
        this.typeName = typeName;
        this.baseState = baseState;
    }

    public String getTypeName() {
        return typeName;
    }

    public SensorBaseState getState() {
        return baseState;
    }

    public static SensorBaseState getSensorState(String typeName){
        for (SensorAnalyseState sensor:values()){
            if (sensor.getTypeName().equals(typeName)){
                return sensor.getState();
            }
        }
        throw new BizException("SENSOR_DATA_ANALYSE_FUNCTION_NOT_FOUND","传感器数据解析算法不存在");
    }
}
