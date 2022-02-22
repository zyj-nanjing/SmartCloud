package www.bwsensing.com.device.extensionpoint;

import com.alibaba.cola.extension.ExtensionPointI;
import www.bwsensing.com.domain.device.model.data.MonitorReceive;
import www.bwsensing.com.device.dto.command.FacilityDataCmd;


/**
 * @author macos-zyj
 */
public interface FacilityDataAnalyseExtPt extends ExtensionPointI {
    /**
     * 分析接收数据
     * @param facilityDataCmd
     * @return
     */
    MonitorReceive analyseFacilityData(FacilityDataCmd facilityDataCmd);
}
