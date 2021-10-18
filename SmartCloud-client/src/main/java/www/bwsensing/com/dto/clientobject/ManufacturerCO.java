package www.bwsensing.com.dto.clientobject;


/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ManufacturerCO {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactureCode() {
        return factureCode;
    }

    public void setFactureCode(String factureCode) {
        this.factureCode = factureCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Boolean getInner() {
        return isInner;
    }

    public void setInner(Boolean inner) {
        isInner = inner;
    }
}
