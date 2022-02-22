package www.bwsensing.com.monitor.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class IndustryFileSortQuery extends PageQuery {
    /**名称*/
    private String name;
    /**行业编码*/
    private String code;
}
