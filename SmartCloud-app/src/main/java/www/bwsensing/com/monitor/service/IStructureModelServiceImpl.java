package www.bwsensing.com.monitor.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageInfo;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import www.bwsensing.com.monitor.api.StructureModelService;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorStructureModelDO;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorStructureModelMapper;
import www.bwsensing.com.monitor.command.MonitorStructureModelSaveCmdExo;
import www.bwsensing.com.monitor.command.MonitorStructureModelUpdateCmdExo;
import www.bwsensing.com.domain.monitor.gateway.StructureModelGateway;
import www.bwsensing.com.monitor.dto.command.StructureModelSaveCmd;
import www.bwsensing.com.monitor.dto.command.StructureModelUpdateCmd;
import www.bwsensing.com.monitor.dto.clientobject.StructureModelCO;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import www.bwsensing.com.monitor.command.StructureImportCmdExo;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.monitor.export.PositionModelVo;
import www.bwsensing.com.monitor.export.StructureModelVo;
import www.bwsensing.com.common.command.BaseQuery;
import static java.util.stream.Collectors.toList;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class IStructureModelServiceImpl implements StructureModelService {
    private static final BeanCopier STRUCTURE_COPIER = BeanCopier.create(MonitorStructureModelDO.class, StructureModelCO.class,false);

    @Resource
    private MonitorStructureModelSaveCmdExo structureSaveCmdExo;

    @Resource
    private MonitorStructureModelUpdateCmdExo structureUpdateCmdExo;

    @Resource
    private MonitorStructureModelMapper structureModelMapper;

    @Resource
    private StructureModelGateway structureModelGateway;

    @Resource
    private StructureImportCmdExo structureModelExo;

    @Resource
    private TokenGateway tokenGateway;

    @Override
    public Response save(@Valid StructureModelSaveCmd saveCmd) {
        return structureSaveCmdExo.execute(saveCmd);
    }

    @Override
    public Response update(@Valid StructureModelUpdateCmd updateCmd) {
        return structureUpdateCmdExo.execute(updateCmd);
    }

    @Override
    public Response deleteStructure(String code) {
        structureModelGateway.deleteByCode(code);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<StructureModelCO> selectStructureShow() {
        List<MonitorStructureModelDO> structureList = structureModelMapper.selectStructureModel(tokenGateway.getTokenInfo().getGroupId());
        return MultiResponse.of(toClientObjects(structureList));
    }

    @Override
    public SingleResponse<ImportResultCO> importStructure(List<StructureModelVo> structureCollection,
                                                          List<PositionModelVo> positionCollection) {
        return structureModelExo.execute(structureCollection,positionCollection);
    }

    @Override
    public PageResponse<StructureModelCO> selectStructurePages(BaseQuery baseQuery) {
        PageHelperUtils<BaseQuery, MonitorStructureModelDO> pageHelper =
                PageHelperUtils.<BaseQuery,MonitorStructureModelDO>builder()
                        .pageFunction((groupQuery)->structureModelMapper.selectStructureModel(tokenGateway.getTokenInfo().getGroupId())).build();
        PageInfo<MonitorStructureModelDO> pageInfo= pageHelper.getPageCollections(baseQuery);
        List<StructureModelCO> result = toClientObjects(pageInfo.getList());
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),baseQuery.getPageIndex() );
    }


    @Override
    public SingleResponse<StructureModelCO> selectStructureById(Integer id) {
        Assert.notNull(id,"Id不能为空");
        MonitorStructureModelDO resultStructure = structureModelMapper.selectStructureModelById(id);
        if (null != resultStructure){
            StructureModelCO clientObject = toClientObject(resultStructure);
            return SingleResponse.of(clientObject);
        } else{
            throw new BizException("STRUCTURE_MODEL_NOT_FOUND","结构体模板不存在");
        }
    }

    @Override
    public SingleResponse<StructureModelCO> selectStructureByCode(String code) {
        Assert.notNull(code,"code不能为空");
        MonitorStructureModelDO resultStructure = structureModelMapper.selectStructureModelByCode(code);
        if (null != resultStructure){
            StructureModelCO clientObject = toClientObject(resultStructure);
            return SingleResponse.of(clientObject);
        } else{
            throw new BizException("STRUCTURE_MODEL_NOT_FOUND","结构体模板不存在");
        }
    }


    private static StructureModelCO toClientObject(MonitorStructureModelDO structureDo){
        StructureModelCO result = new StructureModelCO();
        STRUCTURE_COPIER.copy(structureDo,result,null);
        return result;
    }

    private List<StructureModelCO> toClientObjects(List<MonitorStructureModelDO> resultList){
        return resultList.stream().map(IStructureModelServiceImpl::toClientObject).collect(toList());
    }
}
