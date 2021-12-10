package www.bwsensing.com.command;

import com.alibaba.cola.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.domain.gateway.PrototypeGateway;
import www.bwsensing.com.domain.monitor.MonitorItem;
import www.bwsensing.com.domain.monitor.MonitorPrototype;
import www.bwsensing.com.dto.command.ItemsUpdateCmd;
import www.bwsensing.com.dto.command.PrototypeUpdateCmd;
import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
@Component
public class PrototypeUpdateCmdExo {

    @Resource
    private PrototypeGateway prototypeGateway;


    @Transactional(rollbackFor =  RuntimeException.class)
    public Response execute(PrototypeUpdateCmd cmd){
        MonitorPrototype domain = new MonitorPrototype();
        domain.setId(cmd.getId());
        domain.setTypeName(cmd.getTypeName());
        domain.setOrderSort(cmd.getOrderSort());
        if (null != cmd.getItemsUpdateList()){
            domain.setItemsList(toMonitorItemsList(cmd.getItemsUpdateList()));
        }
        prototypeGateway.updatePrototype(domain);
        return Response.buildSuccess();
    }

    private List<MonitorItem> toMonitorItemsList(List<ItemsUpdateCmd> itemUpdateList){
        return itemUpdateList.stream().map(PrototypeUpdateCmdExo::toDomain).collect(toList());
    }
    private static MonitorItem toDomain(ItemsUpdateCmd saveCmd){
        MonitorItem items = new MonitorItem();
        BeanUtils.copyProperties(saveCmd,items);
        return items;
    }
}
