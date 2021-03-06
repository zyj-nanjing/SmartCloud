package www.bwsensing.com.system.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import lombok.Data;
import www.bwsensing.com.monitor.dto.clientobject.IndustryFieldCO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class SystemClientCO extends DTO {
    /**主键Id*/
    private Integer id;
    /**客户名称*/
    private String clientName;
    /**客户简称*/
    private String clientShortName;
    /**客户品牌标识*/
    private String clientLogo;
    /** 国家地区 */
    private String country;
    /**客户电话*/
    private String clientPhone;
    /**客户传真*/
    private String clientFax;
    /**客户地址*/
    private String clientAddress;
    /**客户邮箱*/
    private String email;
    /** 客户来源 */
    private String comesFrom;
    /** 等级 */
    private String level;
    /** 客户类型 */
    private String clientType;
    /** 业务员 */
    private String salesman;
    /**客户状态*/
    private String clientStatus;
    /**客户描述*/
    private String clientDesc;
    /**相关行业领域*/
    private List<IndustryFieldCO> releaseFields;
    /**所属内部组织结构*/
    private SystemStructureCO innerStructure;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**修改人*/
    private String updater;
    /**修改时间*/
    private Date updateTime;
}
