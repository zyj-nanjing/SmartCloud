package www.bwsensing.com.project.analyse;



import www.bwsensing.com.project.analyse.domain.SensorData;

/**
 * @author macos-zyj
 */
public interface SensorBaseState {
    /**
     * 分析原始数据
     * @param raw
     * @return
     */
    SensorData analyseRawData(String raw);
}
