package www.bwsensing.com.device.convertor;

import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import java.util.ArrayList;
import java.util.List;


/**
 * 设备转换器
 * @author macos-zyj
 */
public class ProductDeviceConvertor {
    private static final BeanCopier DOMAIN_OBJECT_COPIER = BeanCopier.create(ProductDeviceDO.class, ProductDevice.class,false);
    private static final BeanCopier  DATA_OBJECT_COPIER= BeanCopier.create(ProductDevice.class, ProductDeviceDO.class,false);

    public static ProductDevice toDomain(ProductDeviceDO dataObject){
        ProductDevice domainObject = new ProductDevice();
        DOMAIN_OBJECT_COPIER.copy(dataObject,domainObject,null);
        if(null != dataObject.getExtraProductData()){
            domainObject.setExtraProductData(ExtraProductDataItemConvertor.toDomainCollection(dataObject.getExtraProductData()));
        }
        return domainObject;
    }

    public static List<ProductDevice> toDomainCollection(List<ProductDeviceDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(ProductDeviceConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }

    public static ProductDeviceDO toDataObject(ProductDevice domainObject){
        ProductDeviceDO dataObject = new ProductDeviceDO();
        DATA_OBJECT_COPIER.copy(domainObject, dataObject,null);
        return dataObject;
    }
}
