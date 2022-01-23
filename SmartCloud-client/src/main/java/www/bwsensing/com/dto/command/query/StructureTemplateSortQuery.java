package www.bwsensing.com.dto.command.query;

import com.alibaba.cola.dto.Query;
import lombok.Data;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class StructureTemplateSortQuery extends Query {
    /**模板名称*/
    private String templateName;
    /**所属行业*/
    private List<Integer> belowFields;
}
