package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.manufacturer.ProductManufacturer;
import www.bwsensing.com.gatewayimpl.database.dataobject.ProductManufacturerDO;

/**
 * @author macos-zyj
 */
public class ManufacturerConvertor {
    private static final BeanCopier DATA_OBJECT_COPIER =
            BeanCopier.create(ProductManufacturer.class, ProductManufacturerDO.class,false);

    public static ProductManufacturerDO toDataObject(ProductManufacturer domainObject){
        ProductManufacturerDO dataObject = new ProductManufacturerDO();
        DATA_OBJECT_COPIER.copy(domainObject,dataObject,null);
        return dataObject;
    }
}
