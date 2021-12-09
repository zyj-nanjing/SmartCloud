package www.bwsensing.com.common.utills;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * Md5 加密工具类
 * @author macos-zyj
 */
@Slf4j
public class Md5Utils {

    /**
     * MD5加码 生成32位md5码
     * @param inStr
     * @return
     */
    public static String encryptMd5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMd5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);

    }

    /**
     * check
     * @param inputStr 输入的字串
     * @param currentStr 当前密文
     * @return
     */
    public static boolean checkEqual(String inputStr,String currentStr) {
        String md5 = encryptMd5(inputStr);
        return currentStr.equals(md5);
    }

}
