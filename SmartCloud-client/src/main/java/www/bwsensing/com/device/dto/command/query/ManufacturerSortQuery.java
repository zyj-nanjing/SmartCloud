package www.bwsensing.com.device.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class ManufacturerSortQuery extends PageQuery {
    /**名称*/
    private String name;
    /**邮编*/
    private String postCode;
    /**地址*/
    private String address;
    /**联系者*/
    private String connector;
    /**联系电话*/
    private String contactNumber;
    /**是否为北微厂*/
    private Boolean isInner;
}
