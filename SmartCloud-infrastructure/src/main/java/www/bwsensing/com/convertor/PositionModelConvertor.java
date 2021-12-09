package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.monitor.model.MonitorPositionModel;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPositionModelDO;

import java.util.List;

import static java.util.stream.Collectors.toList;
/**
 * @author macos-zyj
 */
public class PositionModelConvertor {
    private static final BeanCopier STRUCTURE_POSITION_COPIER = BeanCopier.create(MonitorPositionModel.class,
            MonitorPositionModelDO.class,false);
    private static final BeanCopier STRUCTURE_POSITION_DOMAIN_COPIER = BeanCopier.create(
            MonitorPositionModelDO.class,MonitorPositionModel.class,false);

    public static MonitorPositionModelDO toDataObject(MonitorPositionModel positionModel){
        MonitorPositionModelDO positionModelDo = new MonitorPositionModelDO();
        STRUCTURE_POSITION_COPIER.copy(positionModel,positionModelDo,null);
        return positionModelDo;
    }

    public static MonitorPositionModel toDomain(MonitorPositionModelDO dataObject){
        MonitorPositionModel positionModel = new MonitorPositionModel();
        STRUCTURE_POSITION_DOMAIN_COPIER.copy(dataObject,positionModel,null);
        return positionModel;
    }

    public static List<MonitorPositionModel> toDomainArray(List<MonitorPositionModelDO> dataList){
        return dataList.stream().map(PositionModelConvertor::toDomain).collect(toList());
    }

}
