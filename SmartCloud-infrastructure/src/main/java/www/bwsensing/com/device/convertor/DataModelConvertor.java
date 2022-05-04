package www.bwsensing.com.device.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.model.data.model.*;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataModelDO;

/**
 * @author macos-zyj
 */
public class DataModelConvertor {
    private static final BeanCopier MODEL_COPIER = BeanCopier.create(ProductDataModel.class, DataModelDO.class,false);
    private static final BeanCopier MODEL_DOMAIN_COPIER = BeanCopier.create(DataModelDO.class,ProductDataModel.class,false);

    public static DataModelDO toDataObject(ProductDataModel dataModel){
        DataModelDO dataModelDo = new DataModelDO();
        MODEL_COPIER.copy(dataModel,dataModelDo,null);
        dataModelDo.setSplitMethod(dataModel.getSplitMethod().getType());
        dataModelDo.setDataForm(dataModel.getDataForm().getType());
        dataModelDo.setMessageType(dataModel.getMessageType().getType());
        return dataModelDo;
    }

    public static List<ProductDataModel> toDomainArray(List<DataModelDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(DataModelConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }



    public static ProductDataModel toDomain(DataModelDO dataObject){
        List<DataModelItem> items = null;
        if (null != dataObject.getDataItems()&& dataObject.getDataItems().size()>0){
            items = DataModelItemConvertor.toDomainArray(dataObject.getDataItems());
            dataObject.getDataItems().clear();
        }
        ProductDataModel dataModel = new ProductDataModel();
        MODEL_DOMAIN_COPIER.copy(dataObject,dataModel,null);
        if( null != items){
            dataModel.setDataItems(items);
        }
        dataModel.setSplitMethod(SplitMethod.getSplitMethod(dataObject.getSplitMethod()));
        dataModel.setDataForm(DataForm.getDataForm(dataObject.getDataForm()));
        dataModel.setMessageType(MessageFormat.getMessageFormat(dataObject.getMessageType()));
        return dataModel;
    }
}
