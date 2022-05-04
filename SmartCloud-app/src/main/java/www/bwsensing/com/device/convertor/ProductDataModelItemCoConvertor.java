package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelItemCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataModelItemDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class ProductDataModelItemCoConvertor {
    private static final BeanCopier CLIENT_COPIER = BeanCopier.create(DataModelItemDO.class, ProductDataModelItemCO.class,false);

    public static List<ProductDataModelItemCO> toClientCollection(List<DataModelItemDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(ProductDataModelItemCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }


    public static ProductDataModelItemCO toClientObject(DataModelItemDO dataObject){
        ProductDataModelItemCO dataItemModel = new ProductDataModelItemCO();
        CLIENT_COPIER.copy(dataObject,dataItemModel,null);
        if (null  != dataObject.getProtoItem()){
            dataItemModel.setItemName(dataObject.getProtoItem().getItemName());
            dataItemModel.setDataItemId(dataObject.getProtoItem().getId());
        }
        return dataItemModel;
    }
}
