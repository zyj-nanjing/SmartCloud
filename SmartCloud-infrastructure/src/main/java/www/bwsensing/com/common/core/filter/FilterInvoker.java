package www.bwsensing.com.common.core.filter;

/**
 * @author mac_zyj
 */
public interface FilterInvoker<T> {

    /**
     * 下一个
     * @param context
     */
    default  void invoke(T context){};
}
