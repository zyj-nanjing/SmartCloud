package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ExtraProductDataItemDO;
import www.bwsensing.com.domain.device.model.ExtraProductDataItem;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class ExtraProductDataItemConvertor {
    private static final BeanCopier ITEM_COPIER = BeanCopier.create(ExtraProductDataItem.class, ExtraProductDataItemDO.class,false);
    private static final BeanCopier ITEM_DATA_COPIER = BeanCopier.create(ExtraProductDataItemDO.class, ExtraProductDataItem.class,false);

    public static ExtraProductDataItem toDomain(ExtraProductDataItemDO dataObject){
        ExtraProductDataItem item = new ExtraProductDataItem();
        ITEM_DATA_COPIER.copy(dataObject,item,null);
        return item;
    }

    public static List<ExtraProductDataItem> toDomainCollection(List<ExtraProductDataItemDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(ExtraProductDataItemConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }

    public static ExtraProductDataItemDO toDataObject(ExtraProductDataItem domainObject){
        ExtraProductDataItemDO item = new ExtraProductDataItemDO();
        ITEM_COPIER.copy(domainObject,item,null);
        return item;
    }
}
