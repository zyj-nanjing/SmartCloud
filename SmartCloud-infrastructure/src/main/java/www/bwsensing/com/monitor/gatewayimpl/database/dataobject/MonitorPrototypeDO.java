package www.bwsensing.com.monitor.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class MonitorPrototypeDO {
    /**主键*/
    private Integer id;
    /**类型名称*/
    private String  typeName;
    /**检测项集合*/
    private List<MonitorItemsDO> itemCollection;
    /**创建人**/
    private String creator;
    /**创建时间**/
    private Date createTime;
    /**修改者**/
    private String updater;
    /**修改时间**/
    private Date  updateTime;
    /**是否有效**/
    private Boolean isEffective;
    /**排序顺序**/
    private Integer orderSort;
}
