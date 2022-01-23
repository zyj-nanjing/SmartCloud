package www.bwsensing.com.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import www.bwsensing.com.api.MonitorService;
import www.bwsensing.com.command.PrototypeAddCmdExo;
import www.bwsensing.com.command.PrototypeUpdateCmdExo;
import www.bwsensing.com.convertor.ItemsCoConvertor;
import www.bwsensing.com.convertor.PrototypeCoConvertor;
import www.bwsensing.com.domain.gateway.PrototypeGateway;
import www.bwsensing.com.dto.command.PrototypeAddCmd;
import www.bwsensing.com.dto.command.query.PrototypeSortQuery;
import www.bwsensing.com.dto.command.PrototypeUpdateCmd;
import www.bwsensing.com.dto.clientobject.PrototypeCO;
import www.bwsensing.com.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.gatewayimpl.database.MonitorPrototypeMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPrototypeDO;
import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class IMonitorServiceImpl implements MonitorService {
    @Resource
    private PrototypeAddCmdExo  prototypeAddCmdExo;

    @Resource
    private PrototypeUpdateCmdExo prototypeUpdateCmdExo;

    @Resource
    private PrototypeGateway portGateway;

    @Resource
    private MonitorPrototypeMapper monitorPrototypeMapper;

    @Resource
    private MonitorItemsMapper monitorItemsMapper;

    @Override
    public Response save(PrototypeAddCmd cmd) {
        return prototypeAddCmdExo.execute(cmd);
    }

    @Override
    public Response update(PrototypeUpdateCmd cmd) {
        return prototypeUpdateCmdExo.execute(cmd);
    }

    @Override
    public Response delete(int typeId) {
        portGateway.delete(typeId);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<PrototypeCO> showAllPrototype() {
        List<MonitorPrototypeDO> prototypeDataList = monitorPrototypeMapper.selectPrototypeBySort(new MonitorPrototypeDO());
        return MultiResponse.of(PrototypeCoConvertor.toCoListByDo(prototypeDataList));
    }

    @Override
    public PageResponse<PrototypeCO> selectPrototypeBySort(PrototypeSortQuery query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        MonitorPrototypeDO querySortQuery = new MonitorPrototypeDO();
        querySortQuery.setTypeName(query.getTypeName());
        List<MonitorPrototypeDO> prototypeDataList = monitorPrototypeMapper.selectPrototypeBySort(querySortQuery);
        PageInfo<MonitorPrototypeDO> pageInfo = new PageInfo<>(prototypeDataList);
        List<PrototypeCO> result = PrototypeCoConvertor.toCoListByDo(prototypeDataList);
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),query.getPageIndex());
    }

    @Override
    public SingleResponse<PrototypeCO> selectPrototypeById(@NotEmpty Integer id) {
        MonitorPrototypeDO resultData = monitorPrototypeMapper.selectPrototypeById(id);
        PrototypeCO responseData = PrototypeCoConvertor.toCo(resultData);
        List<MonitorItemsDO> monitorItemsList = monitorItemsMapper.selectItemsByTypeId(id);
        Assert.notEmpty(monitorItemsList,"标签列表不能为空");
        responseData.setItemsList(ItemsCoConvertor.toClientObjectList(monitorItemsList));
        return SingleResponse.of(responseData);
    }
}
