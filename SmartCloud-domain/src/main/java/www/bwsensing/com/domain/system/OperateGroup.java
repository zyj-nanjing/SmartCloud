package www.bwsensing.com.domain.system;

import lombok.Data;
import www.bwsensing.com.domain.common.UuidUtils;
import www.bwsensing.com.domain.industry.IndustryField;

import java.util.Date;
/**
 * @author macos-zyj
 */
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
