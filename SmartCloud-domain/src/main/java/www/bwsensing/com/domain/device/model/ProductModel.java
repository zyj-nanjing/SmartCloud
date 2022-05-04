package www.bwsensing.com.domain.device.model;

import lombok.Data;
import java.util.Date;
import java.util.List;
import www.bwsensing.com.domain.common.InteractionMode;
import www.bwsensing.com.domain.common.UuidUtils;
import www.bwsensing.com.domain.monitor.model.industry.IndustryField;
import www.bwsensing.com.domain.device.model.data.model.ProductDataModel;
import www.bwsensing.com.domain.device.model.data.model.ProductNetworkModel;
import www.bwsensing.com.domain.device.model.manufacturer.ProductManufacturer;
import www.bwsensing.com.domain.device.model.data.model.DataComputationModel;
/**
 * 产品型号
 * @author macos-zyj
 */
@Data
public class ProductModel {
    private static final double ADD_LENGTH = 0.01;
    /**
     *主键
     */
    private Integer id;

    /**
     *  模型名称
     */
    private String productName;

    /**
     * 是非为北微产品
     */
    private Boolean isInner;


    /**
     * 制造商
     */
    private ProductManufacturer manufacturer;

    /**型号编号**/
    private Integer productKindId;

    /**型号类型**/
    private ProductKind productKind;

    /**型号图片*/
    private String picture;

    /**
     * 直接给到厂家网站路由或商品链接
     */
    private String productDetails;

    /**说明**/
    private String comment;

    /**
     * 监控类型
     */
    private List<ProductDataItem> productItems;

    /**
     * 额外数据项
     */
    private List<ExtraProductDataItem> extraProductItems;

    /**
     * 交互模式
     */
    private InteractionMode interactionMode;

    /**
     * 产品数据模型
     */
    private List<ProductDataModel> productDataModels;

    /**
     * 产品网络模型
     */
    private List<ProductNetworkModel> productNetworkModels;

    /**
     * 产品计算模型
     */
    private List<DataComputationModel> dataComputationModels;

    /**
     * 行业领域
     */
    private List<IndustryField> industryFields;

    /**版本*/
    private Double version;

    private String creator;

    /**创建时间*/
    private Date createTime;

    /**是否有效**/
    private boolean isEffective;

    public void saveOrUpdate(String editor){
        if (null == this.version ){
            this.version = ADD_LENGTH;
        } else{
            this.version += ADD_LENGTH;
        }
        this.creator = editor;
        this.createTime = new Date();
        this.isEffective = true;
    }
}
