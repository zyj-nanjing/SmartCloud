package www.bwsensing.com.monitor.command;

import com.alibaba.cola.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.monitor.gateway.PrototypeGateway;
import www.bwsensing.com.domain.monitor.model.MonitorItem;
import www.bwsensing.com.domain.monitor.model.MonitorPrototype;
import www.bwsensing.com.monitor.dto.command.ItemsSaveCmd;
import www.bwsensing.com.monitor.dto.command.PrototypeAddCmd;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
@Component
public class PrototypeAddCmdExo {

    @Resource
    private PrototypeGateway prototypeGateway;

    public Response execute(PrototypeAddCmd cmd){
        MonitorPrototype domain = new MonitorPrototype();
        domain.setTypeName(cmd.getTypeName());
        domain.setOrderSort(cmd.getOrderSort());
        domain.setItemsList(toMonitorItemsList(cmd.getItemsSaveList()));
        prototypeGateway.createPrototype(domain);
        return Response.buildSuccess();
    }

    private List<MonitorItem> toMonitorItemsList(List<ItemsSaveCmd> itemsSaveList){
        return itemsSaveList.stream().map(PrototypeAddCmdExo::toDomain).collect(toList());
    }
    private static MonitorItem toDomain(ItemsSaveCmd saveCmd){
        MonitorItem items = new MonitorItem();
        BeanUtils.copyProperties(saveCmd,items);
        return items;
    }
}
