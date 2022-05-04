package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.common.InteractionMode;
import www.bwsensing.com.domain.device.model.ProductModel;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductModelDO;

/**
 * @author macos-zyj
 */
public class ProductModelConvertor {
    private static final BeanCopier MODEL_DATA_COPY = BeanCopier.create(ProductModel.class,
            ProductModelDO.class,false);
    private static final BeanCopier MODEL_DOMAIN_COPY = BeanCopier.create(ProductModelDO.class,
            ProductModel.class,false);

    public static ProductModel toDomainObject(ProductModelDO productModelDo){
        ProductModel sensorModel = new ProductModel();
        MODEL_DOMAIN_COPY.copy(productModelDo,sensorModel,null);
        if (null != productModelDo.getInteractionMode()){
            sensorModel.setInteractionMode(InteractionMode.getInteractionMode(productModelDo.getInteractionMode()));
        }
        return sensorModel;
    }


    public static ProductModelDO toDataObject(ProductModel sensorModel){
        ProductModelDO productModelDo = new ProductModelDO();
        MODEL_DATA_COPY.copy(sensorModel, productModelDo,null);
        if (null != sensorModel.getInteractionMode()){
            productModelDo.setInteractionMode(sensorModel.getInteractionMode().getModeCode());
        }
        return productModelDo;
    }
}
