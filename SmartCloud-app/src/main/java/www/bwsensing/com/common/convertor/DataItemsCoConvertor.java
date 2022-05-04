package www.bwsensing.com.common.convertor;

import www.bwsensing.com.common.clientobject.DataItemsCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * 数据项CO转换器
 * @author macos-zyj
 */
public class DataItemsCoConvertor {

    public static DataItemsCO toClientObject(ProductDataItemDO dataItemsDo){
        DataItemsCO clientObject = new DataItemsCO();
        clientObject.setId(dataItemsDo.getId());
        clientObject.setItemName(dataItemsDo.getItemName());
        clientObject.setDataId(dataItemsDo.getDataId());
        clientObject.setUnit(dataItemsDo.getUnit());
        return clientObject;
    }

    public static List<DataItemsCO> toClientCollection(List<ProductDataItemDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(DataItemsCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
