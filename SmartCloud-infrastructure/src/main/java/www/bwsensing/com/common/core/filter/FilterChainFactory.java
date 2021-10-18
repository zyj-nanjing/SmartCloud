package www.bwsensing.com.common.core.filter;

import com.alibaba.cola.domain.ApplicationContextHelper;

/**
 * 责任链模式工厂
 * @author shawnzhan.zxy
 * @date 2018/04/17
 */
public class FilterChainFactory {
    public static<T> FilterChain<T> buildFilterChain(Class... filterClsList) {
        FilterInvoker last = new FilterInvoker(){};
        FilterChain<T> filterChain = new FilterChain<T>();
        for(int i = filterClsList.length - 1; i >= 0; i--){
            FilterInvoker next = last;
            Filter<T> filter;
            try {
                filter = (Filter<T>) ApplicationContextHelper.getBean(filterClsList[i]);
                last = new FilterInvoker<T>(){
                    @Override
                    public void invoke(T context) {
                        filter.doFilter(context, next);
                    }
                };
                filterChain.setHeader(last);
            }catch (Exception e){
                throw new RuntimeException("责任链配置有误");
            }
        }
        return filterChain;
    }
}
