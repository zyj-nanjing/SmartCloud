package www.bwsensing.com.domain.device.manufacturer;

import lombok.Data;
import www.bwsensing.com.domain.common.UuidUtils;

/**
 * 产品厂商
 * @author macos-zyj
 */
@Data
public class ProductManufacturer {
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

    public  void create(){
        this.factureCode = UuidUtils.generateShortUuid();
        if (null == this.getIsInner()){
            this.setIsInner(false);
        }
    }
}
