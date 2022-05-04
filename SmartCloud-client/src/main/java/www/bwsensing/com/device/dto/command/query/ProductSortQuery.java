package www.bwsensing.com.device.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class ProductSortQuery extends PageQuery {
    /**模型编号*/
    private Integer modelId;
    /**项目编号*/
    private Integer projectId;
    /**传感器名称模糊查询*/
    private String name;
    /**管理员编号*/
    private Integer managerId;
    /**按照上线时间排序**/
    private boolean isOrderByOnlineTime;
    /**按照下线时间排序**/
    private boolean isOrderByUpLineTime;
}
