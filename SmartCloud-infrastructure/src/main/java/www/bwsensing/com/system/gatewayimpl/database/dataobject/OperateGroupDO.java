package www.bwsensing.com.system.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class OperateGroupDO {
    /**主键*/
    private Integer id;
    /**组名*/
    private String groupName;
    /**组号*/
    private String groupCode;
    /**人员数量*/
    private Integer personNumber;
    /**是否为内部部门*/
    private Boolean isInner;
    /**公司名称*/
    private String companyName;
    /**是否有效**/
    private Boolean isEnabled;
    /**创建时间**/
    private Date createdTime;
}