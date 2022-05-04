package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ProductDeviceCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
public class ProductCoConvertor {

    private static final BeanCopier DEVICE_COPIER = BeanCopier.create(ProductDeviceDO.class, ProductDeviceCO.class,false);


    public static List<ProductDeviceCO> toClientObjectArray(List<ProductDeviceDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    ProductCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static ProductDeviceCO toClientObject(ProductDeviceDO dataObject){
        ProductDeviceCO clientObject = new ProductDeviceCO();
        DEVICE_COPIER.copy(dataObject, clientObject,null);
        if (null != dataObject.getExtraProductData()){
            clientObject.setExtraProductData(ExtraProductDataItemCoConvertor.toClientCollections(dataObject.getExtraProductData()));
        }
        return clientObject;
    }

}
