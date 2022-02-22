package www.bwsensing.com.project.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author macos-zyj
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemClientSortQuery extends PageQuery {
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
    /** 客户来源 */
    private String comesFrom;
    /** 等级 */
    private String level;
    /** 客户类型 */
    private String clientType;
    /**所属行业编号*/
    private List<Integer> blowFields;
}
