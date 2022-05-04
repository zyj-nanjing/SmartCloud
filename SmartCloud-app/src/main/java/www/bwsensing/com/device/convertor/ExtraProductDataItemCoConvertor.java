package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.ExtraProductDataItemCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ExtraProductDataItemDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * 额外数据项信息转换器
 * @author macos-zyj
 */
public class ExtraProductDataItemCoConvertor {
    private static final BeanCopier EXTRA_ITEM_COPIER = BeanCopier.create(ExtraProductDataItemDO.class, ExtraProductDataItemCO.class,false);


    public static List<ExtraProductDataItemCO> toClientCollections(List<ExtraProductDataItemDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    ExtraProductDataItemCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static ExtraProductDataItemCO toClientObject(ExtraProductDataItemDO dataObject){
        ExtraProductDataItemCO clientObject = new ExtraProductDataItemCO();
        EXTRA_ITEM_COPIER.copy(dataObject, clientObject,null);
        return clientObject;
    }
}
