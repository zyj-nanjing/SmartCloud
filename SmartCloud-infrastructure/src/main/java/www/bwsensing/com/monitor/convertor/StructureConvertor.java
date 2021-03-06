package www.bwsensing.com.monitor.convertor;

import java.util.List;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.monitor.model.MonitorPosition;
import www.bwsensing.com.domain.monitor.model.MonitorStructure;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorStructureDO;

/**
 * @author macos-zyj
 */
public class StructureConvertor {
    private static final BeanCopier STRUCTURE_COPIER = BeanCopier.create(MonitorStructure.class, MonitorStructureDO.class,false);
    private static final BeanCopier STRUCTURE_DOMAIN_COPIER = BeanCopier.create(MonitorStructureDO.class,MonitorStructure.class,false);

    public static MonitorStructureDO toDataObject(MonitorStructure monitorStructure){
        MonitorStructureDO monitorStructureDo = new MonitorStructureDO();
        STRUCTURE_COPIER.copy(monitorStructure,monitorStructureDo,null);
        return monitorStructureDo;
    }

    public static MonitorStructure toDomain(MonitorStructureDO dataObject){
        List<MonitorPosition> positions = null;
        if (null != dataObject.getPositionList()&& dataObject.getPositionList().size()>0){
            positions = PositionConvertor.toDomainArray(dataObject.getPositionList());
            dataObject.getPositionList().clear();
        }
        MonitorStructure monitorStructure = new MonitorStructure();
        STRUCTURE_DOMAIN_COPIER.copy(dataObject,monitorStructure,null);
        if( null != positions){
            monitorStructure.setPositions(positions);
        }
        return monitorStructure;
    }
}
