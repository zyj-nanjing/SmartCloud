package www.bwsensing.com.common.utills;

import lombok.extern.slf4j.Slf4j;
import www.bwsensing.com.common.constant.Constants;
import java.util.Objects;

/**
 * 获取IP方法
 *
 * @author XianYun
 */
@Slf4j
public class IpUtils
{
    private static final String CROSS_ORIGIN_IP_CONTAIN =",";
    /**10.x.x.x/8*/
    private static final byte SECTION_1 = 0x0A;
    /**172.16.x.x/12*/
    private static final byte SECTION_2 = (byte) 0xAC;
    private static final byte SECTION_3 = (byte) 0x10;
    private static final byte SECTION_4 = (byte) 0x1F;
    /**192.168.x.x/16*/
    private static final byte SECTION_5 = (byte) 0xC0;
    private static final byte SECTION_6 = (byte) 0xA8;

    private static final Integer MIN_IP_LENGTH = 1;

    private static final Integer MAX_IP_LENGTH = 3;

    private static final String UNKNOWN="unknown";

    public static boolean internalIp(String ip)
    {
        byte[] ipAddress = textToNumericFormatV4(ip);
        assert (Objects.requireNonNull(ipAddress).length>0);
        return internalIp(ipAddress) || Constants.LOCAL_HOST_IP.equals(ip);
    }

    private static boolean internalIp(byte[] ipAddress)
    {
        if (StringUtils.isNull(ipAddress) || ipAddress.length <= MIN_IP_LENGTH)
        {
            return true;
        }
        final byte b0 = ipAddress[0];
        final byte b1 = ipAddress[1];
        switch (b0)
        {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4)
                {
                    return true;
                }
                break;
            case SECTION_5:
                return b1 == SECTION_6;
            default:
                return false;
        }
        return false;
    }

    /**
     * 将IPv4地址转换成字节
     *
     * @param text IPv4地址
     * @return byte 字节
     */
    public static byte[] textToNumericFormatV4(String text)
    {
        if (text.length() == 0)
        {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try
        {
            long l;
            int i;
            switch (elements.length)
            {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > Constants.UNSIGNED_INT_MAX)){
                        return null;
                    }
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > Constants.UNSIGNED_TINYINT_MAX)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > Constants.UNSIGNED_MEDIUMINT_MAX)) {
                        return null;
                    }
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    if (validByteText(bytes, elements, MIN_IP_LENGTH)) {
                        return null;
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > Constants.UNSIGNED_SMALLINT_MAX)) {
                        return null;
                    }
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    if (validByteText(bytes, elements, MAX_IP_LENGTH)) {
                        return null;
                    }
                    break;
                default:
                    return null;
            }
        }
        catch (NumberFormatException e)
        {
            return null;
        }
        return bytes;
    }

    private static boolean validByteText(byte[] bytes, String[] elements, Integer minIpLength) {
        int i;
        long l;
        for (i = 0; i <= minIpLength; ++i)
        {
            l = Integer.parseInt(elements[i]);
            if ((l < 0L) || (l > Constants.UNSIGNED_TINYINT_MAX )) {
                return true;
            }
            bytes[i] = (byte) (int) (l & 0xFF);
        }
        return false;
    }
}
