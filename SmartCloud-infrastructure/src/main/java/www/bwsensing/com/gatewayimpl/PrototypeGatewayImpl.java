package www.bwsensing.com.gatewayimpl;

import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.convertor.PrototypeConvertor;
import www.bwsensing.com.domain.gateway.PrototypeGateway;
import www.bwsensing.com.domain.monitor.MonitorItem;
import www.bwsensing.com.domain.monitor.MonitorPrototype;
import www.bwsensing.com.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.gatewayimpl.database.MonitorPrototypeMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPrototypeDO;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class PrototypeGatewayImpl implements PrototypeGateway {
    @Resource
    private MonitorItemsMapper monitorItemsMapper;

    @Resource
    private MonitorPrototypeMapper monitorPrototypeMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void createPrototype(MonitorPrototype monitorPrototype) {
        monitorPrototype.create();
        MonitorPrototypeDO saveType = PrototypeConvertor.toDataObject(monitorPrototype);
        monitorPrototype.getItemsList().forEach(item ->{
            monitorPrototypeMapper.addPrototypeLink(saveType.getId(), item.getId());
        });
        monitorPrototypeMapper.save(saveType);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updatePrototype(MonitorPrototype monitorPrototype) {
        monitorPrototype.update();
        monitorPrototypeMapper.update(PrototypeConvertor.toDataObject(monitorPrototype));
        monitorItemsMapper.deleteTypeLinkById(monitorPrototype.getId());
        monitorPrototype.getItemsList().forEach(item ->{
            monitorPrototypeMapper.addPrototypeLink(monitorPrototype.getId(), item.getId());
        });
    }

    @Override
    public void delete(Integer id) {
        monitorPrototypeMapper.delete(id);
        monitorItemsMapper.deleteTypeLinkById(id);
    }

    @Override
    public List<MonitorPrototype> getMonitorPrototypesByIds(List<Integer> ids) {
        Assert.notEmpty(ids,"监测原型编号不能为空!");
        List<MonitorPrototype> prototypes = new ArrayList<>(ids.size());
        ids.forEach(typeId ->{
            MonitorPrototypeDO prototypeDo = monitorPrototypeMapper.selectPrototypeById(typeId);
            if (null != prototypeDo){
                prototypes.add(PrototypeConvertor.toDomain(prototypeDo));
            }
        });
        return prototypes;
    }

    private boolean checkItemContain(List<MonitorItem> itemsList, int id){
        for (MonitorItem item:itemsList){
            if (null != item.getId() && id == item.getId()){
                return true;
            }
        }
        return false;
    }
}
