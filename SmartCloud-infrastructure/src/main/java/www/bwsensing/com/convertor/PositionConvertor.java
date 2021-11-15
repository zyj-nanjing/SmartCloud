package www.bwsensing.com.convertor;

import java.util.List;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.monitor.MonitorPosition;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPositionDO;
import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class PositionConvertor {
    private static final BeanCopier STRUCTURE_POSITION_COPIER = BeanCopier.create(MonitorPosition.class,
            MonitorPositionDO.class,false);
    private static final BeanCopier STRUCTURE_POSITION_DOMAIN_COPIER = BeanCopier.create(
            MonitorPositionDO.class,MonitorPosition.class,false);

    public static MonitorPositionDO toDataObject(MonitorPosition position){
        MonitorPositionDO positionDo = new MonitorPositionDO();
        STRUCTURE_POSITION_COPIER.copy(position,positionDo,null);
        return positionDo;
    }

    public static MonitorPosition toDomain(MonitorPositionDO dataObject){
        MonitorPosition positionModel = new MonitorPosition();
        STRUCTURE_POSITION_DOMAIN_COPIER.copy(dataObject,positionModel,null);
        return positionModel;
    }
    public static List<MonitorPosition> toDomainArray(List<MonitorPositionDO> dataList){
        return dataList.stream().map(PositionConvertor::toDomain).collect(toList());
    }
}
