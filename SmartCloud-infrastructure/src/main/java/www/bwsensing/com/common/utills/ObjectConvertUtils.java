package www.bwsensing.com.common.utills;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

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

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
