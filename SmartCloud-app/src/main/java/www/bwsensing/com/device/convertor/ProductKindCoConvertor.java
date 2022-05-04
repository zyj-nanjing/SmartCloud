package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ProductKindCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductKindDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class ProductKindCoConvertor {
    private static final BeanCopier COPIER = BeanCopier.create(ProductKindDO.class, ProductKindCO.class,false);

    public static ProductKindCO toClientObject(ProductKindDO dataObject){
        ProductKindCO clientObject = new ProductKindCO();
        COPIER.copy(dataObject,clientObject,null);
        return  clientObject;
    }

    public static List<ProductKindCO> toClientCollection(List<ProductKindDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(ProductKindCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
