package www.bwsensing.com.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;
import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class SensorDataSortQuery extends PageQuery {
    /**设备唯一ID**/
    private String snCode;
    /**结构物ID**/
    private Integer structureId;
    /**查询起始时间**/
    private Date startTime;
    /**查询结束时间**/
    private Date endTime;
    /**数据类型**/
    private Integer dataType;
}
