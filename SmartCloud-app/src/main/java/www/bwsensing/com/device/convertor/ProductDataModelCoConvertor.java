package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelCO;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelItemCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataModelDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * 产品数据转转器
 * @author macos-zyj
 */
public class ProductDataModelCoConvertor {
    private static final BeanCopier CLIENT_COPIER = BeanCopier.create(DataModelDO.class,ProductDataModelCO.class,false);

    public static List<ProductDataModelCO> toClientCollection(List<DataModelDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(ProductDataModelCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }


    public static ProductDataModelCO toClientObject(DataModelDO dataObject){
        List<ProductDataModelItemCO> items = null;
        if (null != dataObject.getDataItems()&& dataObject.getDataItems().size()>0){
            items = ProductDataModelItemCoConvertor.toClientCollection(dataObject.getDataItems());
            dataObject.getDataItems().clear();
        }
        ProductDataModelCO dataModel = new ProductDataModelCO();
        CLIENT_COPIER.copy(dataObject,dataModel,null);
        if( null != items){
            dataModel.setDataItems(items);
        }
        return dataModel;
    }
}
