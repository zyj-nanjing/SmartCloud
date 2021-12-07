package www.bwsensing.com.common.utills;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
public class SensorUtils {
    private static final Integer SN_LENGTH = 9;
    private static final String ASCII_SPLIT = ",";

    public static String hexSnAnalyse(String bitText){
        int snNumber = Integer.parseInt(bitText,16);
        String snNumbers = Integer.toString(snNumber);
        Assert.isTrue(snNumbers.length() <= SN_LENGTH,"sn码格式过长");
        StringBuilder result = new StringBuilder("1");
        for (int i = 1; i < SN_LENGTH-snNumbers.length();i++){
            result.append("0");
        }
        return result.append(snNumbers).toString();
    }

    public static  String asciiSnAnalyse(String textCode){
        return textCode.replace("+","");
    }

    public static String getSnCode(List<String> dataCollection){
        dataCollection.removeAll(Collections.singleton(null));
        return analyseSensorSn(dataCollection.get(0));
    }


    public static String analyseSensorSn(String sensorRaw){
        if (!sensorRaw.contains(ASCII_SPLIT)){
            try {
                String currentBitCode = sensorRaw.substring(0, 2);
                return SensorUtils.hexSnAnalyse(currentBitCode);
            } catch (Exception ex){
                log.warn("is not hex");
            }
        }
        String[] dataArray = sensorRaw.split(",");
        return SensorUtils.asciiSnAnalyse(dataArray[0]);
    }
}
