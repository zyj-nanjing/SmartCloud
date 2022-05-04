package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.IndustryFieldDO;
import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class ProductModelDO {
    /**主键**/
    private Integer id;
    /**模型名称*/
    private String productName;
    /**模型类型**/
    private Integer productKindId;
    /**型号图片*/
    private String picture;
    /**产品厂商*/
    private Integer manufacturerId;
    /**说明**/
    private String comment;
    /**
     * 直接给到厂家网站路由或商品链接
     */
    private String productDetails;
    /**
     * 交互方式
     */
    private String interactionMode;

    /**版本*/
    private Double version;
    /**
     * 所属行业领域
     */
    private List<IndustryFieldDO> industryFields;
    /**创建者*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改者*/
    private String updater;
    /**修改时间*/
    private Date updateTime;
    /**是否有效**/
    private Boolean isEffective;
}
