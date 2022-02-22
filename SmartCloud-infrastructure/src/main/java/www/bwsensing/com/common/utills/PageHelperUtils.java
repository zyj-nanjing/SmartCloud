package www.bwsensing.com.common.utills;

import com.alibaba.cola.dto.PageQuery;
import com.github.pagehelper.PageHelper;
import java.util.List;
import java.util.function.Function;
import com.github.pagehelper.PageInfo;
import lombok.Builder;

/**
 * 分页查询工具类
 * 参考自 Java技术驿站
 * @author macos-zyj
 */
@Builder
public class PageHelperUtils<P,R> {
    private Function<P, List<R>> pageFunction;

    /**
     * 获取分页数据
     * @param request
     * @return
     */
    public List<R> getCollections(P request) {
        PageQuery query = (PageQuery) request;
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        return pageFunction.apply(request);
    }

    public PageInfo<R> getPageCollections(P request){
        List<R> dataCollection = this.getCollections(request);
        return new PageInfo<>(dataCollection);
    }
}
