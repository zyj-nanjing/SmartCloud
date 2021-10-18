package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class ProductManufacturerDO {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**厂商编码*/
    private String factureCode;
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
