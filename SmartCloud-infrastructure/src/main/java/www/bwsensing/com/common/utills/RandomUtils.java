package www.bwsensing.com.common.utills;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author ytt
 */
public class RandomUtils {
    private static String randString = "";
    private static final int MAX_LENGTH = 20000;
    private static final char REPLACE_CHAR =',';
    public synchronized static String getNo(int k)
    {
        if (randString.length() > MAX_LENGTH)
        {
            randString = "";
        }
        String rno = getNoNo(k);
        while (randString.contains(rno + REPLACE_CHAR))
        {
            rno = getNoNo(k);
        }
        randString += rno + ",";
        return rno;
    }

    private static String getNoNo(int k)
    {
        try
        {
            Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return RandomStringUtils.randomNumeric(k);
    }
}
