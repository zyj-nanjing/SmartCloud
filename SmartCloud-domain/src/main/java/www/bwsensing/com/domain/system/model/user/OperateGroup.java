package www.bwsensing.com.domain.system.model.user;

import lombok.Data;
import java.util.Date;
import www.bwsensing.com.domain.common.UuidUtils;
import www.bwsensing.com.domain.monitor.model.industry.IndustryField;
/**
 * @author macos-zyj
 */
@Deprecated
@Data
public class OperateGroup {
    /**主键*/
    private Integer id;
    /**组名*/
    private String groupName;
    /**组号*/
    private String groupCode;
    /**人员数量*/
    private Integer personNumber;
    /**行业领域**/
    private IndustryField industryField;
    /**公钥*/
    private String publicKey;
    /**是否为内部部门*/
    private Boolean isInner;
    /**公司名称*/
    private String companyName;
    /**是否有效**/
    private Boolean isEnabled;
    /**创建时间**/
    private Date createdTime;

    /**
     * 初始化方法
     * 注北微管理组 不存在 行业概念
     *  其余的小组创建时持有行业语义
     */
    public void create(){
        this.groupCode = UuidUtils.generateShortUuid();
    }
}
