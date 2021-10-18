package www.bwsensing.com.common.utills;


import org.springframework.util.Assert;

/**
 * @author macos-zyj
 */
public class SensorUtils {
    private static final Integer SN_LENGTH = 9;

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
}
