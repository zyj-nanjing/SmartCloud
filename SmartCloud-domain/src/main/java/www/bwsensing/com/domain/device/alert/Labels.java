package www.bwsensing.com.domain.device.alert;

import lombok.Data;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author macos-zyj
 */
@Data
public class Labels{
    private String ruleName;
    private String alertName;

    public Labels() {
    }

    public Labels(String ruleName) {
        this.ruleName = ruleName;
    }

    public Map<String, String> toLabels(){
        Map<String, String> labelMap = new LinkedHashMap<>(16);
        labelMap.put("ruleName",ruleName);
        return labelMap;
    }
}