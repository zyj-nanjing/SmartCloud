package www.bwsensing.com.device.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import www.bwsensing.com.domain.device.model.DataItemAttribute;
import www.bwsensing.com.domain.device.model.DataItemSourceKind;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;


/**
 * 转换器
 * @author macos-zyj
 */
public class ProductDataItemConvertor {
    private static final BeanCopier ITEM_COPIER = BeanCopier.create(ProductDataItem.class, ProductDataItemDO.class,false);
    private static final BeanCopier ITEM_DATA_COPIER = BeanCopier.create(ProductDataItemDO.class, ProductDataItem.class,false);


    public static List<ProductDataItem> toDomainCollection(List<ProductDataItemDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(ProductDataItemConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }

    public static ProductDataItem toDomain(ProductDataItemDO dataObject){
        ProductDataItem item = new ProductDataItem();
        ITEM_DATA_COPIER.copy(dataObject,item,null);
        if(null !=dataObject.getItemKind()){
            item.setItemKind(DataItemSourceKind.getItemSourceKind(dataObject.getItemKind()));
        }
        if(null !=dataObject.getItemAttribute()){
            item.setItemAttribute(DataItemAttribute.getDataItemAttribute(dataObject.getItemAttribute()));
        }
        return item;
    }

    public static ProductDataItemDO toDataObject(ProductDataItem domainObject){
        ProductDataItemDO item = new ProductDataItemDO();
        ITEM_COPIER.copy(domainObject,item,null);
        if(null !=domainObject.getItemKind()){
            item.setItemKind(domainObject.getItemKind().getType());
        }
        if(null !=domainObject.getItemAttribute()){
            item.setItemAttribute(domainObject.getItemAttribute().getType());
        }
        item.setItemKind(domainObject.getItemKind().getType());
        return item;
    }
}
