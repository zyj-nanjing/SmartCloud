package www.bwsensing.com.device.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ProductDataItemCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;


/**
 * @author macos-zyj
 */
public class ProductDataItemCoConvertor {
    private static final BeanCopier CLIENT_COPIER = BeanCopier.create(ProductDataItemDO.class, ProductDataItemCO.class,false);

    public static List<ProductDataItemCO> toClientCollection(List<ProductDataItemDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(ProductDataItemCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }


    public static ProductDataItemCO toClientObject(ProductDataItemDO dataObject){
        ProductDataItemCO dataItemModel = new ProductDataItemCO();
        CLIENT_COPIER.copy(dataObject,dataItemModel,null);
        return dataItemModel;
    }
}
