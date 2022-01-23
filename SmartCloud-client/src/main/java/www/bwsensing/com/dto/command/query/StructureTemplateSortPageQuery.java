package www.bwsensing.com.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import java.util.List;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class StructureTemplateSortPageQuery extends PageQuery {
    /**模板名称*/
    private String templateName;
    /**所属行业*/
    private List<Integer> belowFields;
}
