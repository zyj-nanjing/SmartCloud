package www.bwsensing.com.domain.device.alert;


import lombok.Data;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author macos-zyj
 */
@Data
public class Annotations{
    private String info;
    private String summary;
    public Annotations() {

    }

    public Annotations(String info, String summary) {
        this.info = info;
        this.summary = summary;
    }

    public Map<String, String> toAnnotations(){
        Map<String, String> annotationMap = new LinkedHashMap<>(16);
        annotationMap.put("info",info);
        annotationMap.put("summary",summary);
        return annotationMap;
    }
}
