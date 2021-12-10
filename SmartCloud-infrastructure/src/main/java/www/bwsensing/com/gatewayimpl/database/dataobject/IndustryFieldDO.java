package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;

/**
 * 行业领域实体
 * @author macos-zyj
 */
@Data
public class IndustryFieldDO {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**行业编码*/
    private String code;
    /**创建时间*/
    private Date createTime;
}
