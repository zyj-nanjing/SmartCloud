package www.bwsensing.com.common.convertor;

import www.bwsensing.com.common.clientobject.DataItemsCO;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorItemsDO;

/**
 * 数据项CO转换器
 * @author macos-zyj
 */
public class DataItemsCoConvertor {

    public static DataItemsCO toDataItemsCo(MonitorItemsDO dataItemsDo){
        DataItemsCO clientObject = new DataItemsCO();
        clientObject.setId(dataItemsDo.getId());
        clientObject.setItemName(dataItemsDo.getItemName());
        clientObject.setDataId(dataItemsDo.getDataId());
        clientObject.setUnit(dataItemsDo.getUnit());
        return clientObject;
    }
}
