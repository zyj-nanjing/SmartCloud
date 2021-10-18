package www.bwsensing.com.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import www.bwsensing.com.api.IStructureModelService;
import www.bwsensing.com.command.MonitorStructureModelSaveCmdExo;
import www.bwsensing.com.command.MonitorStructureModelUpdateCmdExo;
import www.bwsensing.com.command.StructureImportCmdExo;
import www.bwsensing.com.domain.gateway.StructureModelGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.dto.PositionModelVo;
import www.bwsensing.com.dto.StructureModelVo;
import www.bwsensing.com.dto.clientobject.ImportResultCO;
import www.bwsensing.com.dto.command.query.BaseQuery;
import static java.util.stream.Collectors.toList;
import www.bwsensing.com.dto.command.StructureModelSaveCmd;
import www.bwsensing.com.dto.command.StructureModelUpdateCmd;
import www.bwsensing.com.dto.clientobject.StructureModelCO;
import www.bwsensing.com.gatewayimpl.database.MonitorStructureModelMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorStructureModelDO;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class StructureModelServiceImpl implements IStructureModelService {
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
        PageHelper.startPage(baseQuery.getPageIndex(), baseQuery.getPageSize());
        List<MonitorStructureModelDO> resultList = structureModelMapper.selectStructureModel(tokenGateway.getTokenInfo().getGroupId());
        PageInfo<MonitorStructureModelDO> pageInfo = new PageInfo<>(resultList);
        List<StructureModelCO> result = toClientObjects(resultList);
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
        return resultList.stream().map(StructureModelServiceImpl::toClientObject).collect(toList());
    }
}
