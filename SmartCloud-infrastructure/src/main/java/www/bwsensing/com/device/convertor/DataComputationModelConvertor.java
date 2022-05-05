package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import static java.util.stream.Collectors.toList;
import www.bwsensing.com.domain.device.model.data.model.*;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataComputationModelDO;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class DataComputationModelConvertor {
    private static final BeanCopier MODEL_COPIER = BeanCopier.create(DataComputationModel.class, DataComputationModelDO.class,false);
    private static final BeanCopier MODEL_DOMAIN_COPIER = BeanCopier.create(DataComputationModelDO.class,DataComputationModel.class,false);

    public static DataComputationModelDO toDataObject(DataComputationModel dataModel){
        DataComputationModelDO dataModelDo = new DataComputationModelDO();
        MODEL_COPIER.copy(dataModel,dataModelDo,null);
        return dataModelDo;
    }

    public static List<DataComputationModel> toDomainArray(List<DataComputationModelDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(DataComputationModelConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }



    public static DataComputationModel toDomain(DataComputationModelDO dataObject){
        DataComputationModel dataModel = new DataComputationModel();
        MODEL_DOMAIN_COPIER.copy(dataObject,dataModel,null);
        if (null != dataObject.getProductDataItems()){
            dataModel.setProductDataItems(ProductDataItemConvertor.toDomainCollection(dataObject.getProductDataItems()));
        }
        return dataModel;
    }
}
