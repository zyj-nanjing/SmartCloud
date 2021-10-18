package www.bwsensing.com.dto.command.query;

import lombok.Data;

/**
 * 检测项查询
 * @author macos-zyj
 */
@Data
public class ItemsQuery {
    /**模型编号*/
    private Integer modelId;
    /**类型编号*/
    private Integer typeIdId;
}
