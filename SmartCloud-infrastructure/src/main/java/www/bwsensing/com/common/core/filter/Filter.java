package www.bwsensing.com.common.core.filter;

/**
 * @author mac_zyj
 */
public interface Filter<T> {
    /**
     *拦截
     * @param context
     * @param nextFilter
     */
    void doFilter(T context, FilterInvoker nextFilter);
}
