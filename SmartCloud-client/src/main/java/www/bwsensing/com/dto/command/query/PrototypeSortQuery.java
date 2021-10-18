package www.bwsensing.com.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class PrototypeSortQuery extends PageQuery {
    private String  typeName;
}
