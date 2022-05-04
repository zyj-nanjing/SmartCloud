package www.bwsensing.com.device.convertor;

import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ProductModelCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductModelDO;
import java.util.ArrayList;
import java.util.List;

/**
 * 传感器模板转换
 * @author macos-zyj
 */
public class ProductModelCoConvertor {
    private static final BeanCopier SENSOR_MODEL_DATA__COPIER = BeanCopier.create(ProductModelDO.class, ProductModelCO.class,false);

    public static List<ProductModelCO> toClientObjectArray(List<ProductModelDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    ProductModelCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static ProductModelCO toClientObject(ProductModelDO sensorModel){
        ProductModelCO productModelCo = new ProductModelCO();
        SENSOR_MODEL_DATA__COPIER.copy(sensorModel, productModelCo,null);
        return productModelCo;
    }
}
