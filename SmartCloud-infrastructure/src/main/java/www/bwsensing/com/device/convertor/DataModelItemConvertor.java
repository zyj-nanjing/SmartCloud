package www.bwsensing.com.device.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataModelItemDO;
import www.bwsensing.com.domain.device.model.data.model.DataItemKind;
import www.bwsensing.com.domain.device.model.data.model.DataModelItem;
import www.bwsensing.com.domain.device.model.data.model.DataType;
import www.bwsensing.com.monitor.convertor.ItemsConvertor;

/**
 * @author macos-zyj
 */
public class DataModelItemConvertor {
    private static final BeanCopier DOMAIN_OBJECT_COPIER = BeanCopier.create(DataModelItemDO.class, DataModelItem.class,false);
    private static final BeanCopier  DATA_OBJECT_COPIER= BeanCopier.create(DataModelItem.class, DataModelItemDO.class,false);

    public static DataModelItem toDomain(DataModelItemDO dataObject){
        DataModelItem domainObject = new DataModelItem();
        DOMAIN_OBJECT_COPIER.copy(dataObject,domainObject,null);
        if (null != dataObject.getItemKind() ){
            domainObject.setItemKind(DataItemKind.getDataItemKind(dataObject.getItemKind()));
        }
        domainObject.setDataType(DataType.getDataType(dataObject.getDataType()));
        if (null != dataObject.getProtoItem() ){
            domainObject.setProtoItem(ItemsConvertor.toDomain(dataObject.getProtoItem() ));
        }
        return domainObject;
    }

    public static List<DataModelItem> toDomainArray(List<DataModelItemDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(DataModelItemConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }

    public static DataModelItemDO toDataObject(DataModelItem domainObject){
        DataModelItemDO modelItemDo = new DataModelItemDO();
        DATA_OBJECT_COPIER.copy(domainObject,modelItemDo,null);
        if (null  != domainObject.getItemKind()){
            modelItemDo.setItemKind(domainObject.getItemKind().getType()+"");
        }
        modelItemDo.setDataType(domainObject.getDataType().getType());
        return modelItemDo;
    }
}
