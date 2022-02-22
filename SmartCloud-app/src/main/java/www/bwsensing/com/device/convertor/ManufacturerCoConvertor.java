package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ManufacturerCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductManufacturerDO;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class ManufacturerCoConvertor {
    private static final BeanCopier CLIENT_OBJECT_COPIER = BeanCopier.create(ProductManufacturerDO.class, ManufacturerCO.class,false);

    public static  ManufacturerCO toClientObject(ProductManufacturerDO dataObject){
        ManufacturerCO client = new ManufacturerCO();
        CLIENT_OBJECT_COPIER.copy(dataObject,client,null);
        return client;
    }


    public static List<ManufacturerCO> toClientObjectCollection(List<ProductManufacturerDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(ManufacturerCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
