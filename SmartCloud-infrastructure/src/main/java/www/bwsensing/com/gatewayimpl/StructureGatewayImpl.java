package www.bwsensing.com.gatewayimpl;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import www.bwsensing.com.convertor.PositionConvertor;
import www.bwsensing.com.convertor.StructureConvertor;
import www.bwsensing.com.domain.monitor.MonitorPosition;
import www.bwsensing.com.domain.monitor.MonitorStructure;
import www.bwsensing.com.domain.gateway.StructureGateway;
import www.bwsensing.com.gatewayimpl.database.MonitorPositionMapper;
import www.bwsensing.com.gatewayimpl.database.MonitorStructureMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPositionDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorStructureDO;

import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class StructureGatewayImpl implements StructureGateway {
    @Resource
    private MonitorStructureMapper structureMapper;
    @Resource
    private MonitorPositionMapper positionMapper;

    @Override
    public void saveStructure(MonitorStructure monitorStructure) {
        MonitorStructureDO structureDo = StructureConvertor.toDataObject(monitorStructure);
        structureMapper.save(structureDo);
        monitorStructure.getPositions().forEach(position -> {
            position.setStructureId(structureDo.getId());
            savePosition(position);
        });
    }

    @Override
    public void savePosition(MonitorPosition monitorPosition) {
        MonitorPositionDO positionDO = PositionConvertor.toDataObject(monitorPosition);
        positionMapper.save(positionDO);
    }

    @Override
    public void updateStructure(MonitorStructure monitorStructure) {
        MonitorStructureDO structureDo = StructureConvertor.toDataObject(monitorStructure);
        List<MonitorPositionDO> positionList = positionMapper.getPositionsByStructureId(structureDo.getId());
        structureMapper.update(structureDo);
        monitorStructure.getPositions().forEach(position -> {
            if (null != position.getId()){
                positionMapper.update(PositionConvertor.toDataObject(position));
                deleteFromPosition(position.getId(),positionList);
            } else{
                positionMapper.save(PositionConvertor.toDataObject(position));
            }
        });
        positionList.forEach(position -> positionMapper.delete(position.getId()));
    }
    
    private void deleteFromPosition(Integer id,List<MonitorPositionDO> positionList){
        for (int i=0;i<positionList.size();i++){
            if (positionList.get(i).getId().equals(id)){
                positionList.remove(i);
            }
        }
    }

    @Override
    public MonitorStructure getMonitorStructureById(Integer structureId) {
        MonitorStructureDO structureDO = structureMapper.selectStructureById(structureId);
        return StructureConvertor.toDomain(structureDO);
    }
}
