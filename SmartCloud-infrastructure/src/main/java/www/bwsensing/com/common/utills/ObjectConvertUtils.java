package www.bwsensing.com.common.utills;

import java.lang.reflect.Array;

/**
 * @author macos-zyj
 */
public class ObjectConvertUtils {
    public static <T> T[] convertArray(Class<T> targetType, Object[] objects) {
        if (targetType == null) {
            return (T[]) objects;
        }
        if (objects == null) {
            return null;
        }
        T[] targetArray = (T[]) Array.newInstance(targetType, objects.length);
        try {
            System.arraycopy(objects, 0, targetArray, 0, objects.length);
        } catch (ArrayStoreException e) {
            e.printStackTrace();
        }
        return targetArray;
    }
}
