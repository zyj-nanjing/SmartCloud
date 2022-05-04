package www.bwsensing.com.device.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.DataComputationModelCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataComputationModelDO;

/**
 * 计算模型转换器
 * @author macos-zyj
 */
public class DataComputationModelCoConvertor {
    private static final BeanCopier CLIENT_OBJECT_COPIER = BeanCopier.create(DataComputationModelDO.class, DataComputationModelCO.class,false);


    public static List<DataComputationModelCO> toClientCollections(List<DataComputationModelDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    DataComputationModelCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static DataComputationModelCO toClientObject(DataComputationModelDO dataObject){
        DataComputationModelCO clientObject = new DataComputationModelCO();
        CLIENT_OBJECT_COPIER.copy(dataObject, clientObject,null);
        if (null != dataObject.getExtraProductDataItems()){
            clientObject.setExtraProductDataItems(ExtraProductDataItemCoConvertor.toClientCollections(dataObject.getExtraProductDataItems()));
        }
        if (null != dataObject.getProductDataItems()){
            clientObject.setProductDataItems(ProductDataItemCoConvertor.toClientCollection(dataObject.getProductDataItems()));
        }
        return clientObject;
    }
}
