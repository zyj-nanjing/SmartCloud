package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ProductNetworkModelCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductNetworkModelDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class ProductNetworkModelCoConvertor {
    private static final BeanCopier DATA__COPIER = BeanCopier.create(ProductNetworkModelDO.class, ProductNetworkModelCO.class,false);

    public static List<ProductNetworkModelCO> toClientCollection(List<ProductNetworkModelDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    ProductNetworkModelCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static ProductNetworkModelCO toClientObject(ProductNetworkModelDO dataObject){
        ProductNetworkModelCO clientObject = new ProductNetworkModelCO();
        DATA__COPIER.copy(dataObject, clientObject,null);
        return clientObject;
    }
}
