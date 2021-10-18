package www.bwsensing.com.common.core.filter;
/**
 * @author mac_zyj
 */
public class FilterChain<T>{

    private FilterInvoker header;

    public void doFilter(T context){
        header.invoke(context);
    }

    public void setHeader(FilterInvoker header) {
        this.header = header;
    }
}
