package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.monitor.model.MonitorPositionModel;
import www.bwsensing.com.domain.monitor.model.MonitorStructureModel;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorStructureModelDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public class StructureModelConvertor {

    private static final BeanCopier STRUCTURE_COPIER = BeanCopier.create(MonitorStructureModel.class, MonitorStructureModelDO.class,false);
    private static final BeanCopier STRUCTURE_DOMAIN_COPIER = BeanCopier.create(MonitorStructureModelDO.class,MonitorStructureModel.class,false);

    public static MonitorStructureModelDO toDataObject(MonitorStructureModel monitorStructure){
        MonitorStructureModelDO monitorStructureDo = new MonitorStructureModelDO();
        STRUCTURE_COPIER.copy(monitorStructure,monitorStructureDo,null);
        return monitorStructureDo;
    }

    public static MonitorStructureModel toDomain(MonitorStructureModelDO dataObject){
        List<MonitorPositionModel> positions = null;
        if (null != dataObject.getPositionList()&& dataObject.getPositionList().size()>0){
            positions = PositionModelConvertor.toDomainArray(dataObject.getPositionList());
            dataObject.getPositionList().clear();
        }
        MonitorStructureModel monitorStructure = new MonitorStructureModel();
        STRUCTURE_DOMAIN_COPIER.copy(dataObject,monitorStructure,null);
        if( null != positions){
            monitorStructure.setPositionList(positions);
        }
        return monitorStructure;
    }
}
