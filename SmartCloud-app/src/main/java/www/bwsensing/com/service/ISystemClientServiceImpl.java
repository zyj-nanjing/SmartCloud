package www.bwsensing.com.service;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.SystemClientService;
import www.bwsensing.com.dto.clientobject.SystemClientCO;
import www.bwsensing.com.dto.command.SystemClientSaveCmd;
import www.bwsensing.com.convertor.SystemClientCoConvertor;
import www.bwsensing.com.domain.system.client.SystemClient;
import www.bwsensing.com.dto.command.SystemClientUpdateCmd;
import www.bwsensing.com.domain.gateway.SystemClientGateway;
import www.bwsensing.com.domain.gateway.IndustryFieldGateway;
import www.bwsensing.com.gatewayimpl.database.SystemClientMapper;
import www.bwsensing.com.dto.command.query.SystemClientSortQuery;
import www.bwsensing.com.domain.system.organization.SystemStructure;
import www.bwsensing.com.domain.gateway.SysStructureTemplateGateway;
import www.bwsensing.com.domain.system.organization.StructureTemplate;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemClientDO;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class ISystemClientServiceImpl implements SystemClientService {
    @Resource
    private SystemClientGateway clientGateway;
    @Resource
    private SysStructureTemplateGateway structureTemplateGateway;
    @Resource
    private IndustryFieldGateway industryFieldGateway;
    @Resource
    private SystemClientMapper systemClientMapper;

    @Override
    public Response addSystemClient(SystemClientSaveCmd saveCmd) {
        SystemClient systemClient = new SystemClient();
        BeanUtils.copyProperties(saveCmd, systemClient);
        SystemStructure systemStructure;
        if (null != saveCmd.getStructureTemplateId()){
            StructureTemplate structureTemplate = structureTemplateGateway.getStructureTemplateById(saveCmd.getStructureTemplateId());
            if (null  == structureTemplate) {
                throw new BizException("STRUCTURE_NOT_FOUND","组织机构模板不存在!");
            }
            systemStructure = structureTemplate.initializeStructure(systemClient.getStructureName());
        } else {
            systemStructure = new SystemStructure(systemClient.getStructureName());
        }
        systemClient.setInnerStructure(systemStructure);
        systemClient.setReleaseFields(industryFieldGateway.getIndustryFieldsByIds(saveCmd.getReleaseFields()));
        clientGateway.saveSystemClient(systemClient);
        return Response.buildSuccess();
    }

    @Override
    public Response updateSystemClient(SystemClientUpdateCmd updateCmd) {
        SystemClient systemClient = new SystemClient();
        BeanUtils.copyProperties(updateCmd, systemClient);
        clientGateway.updateSystemClient(systemClient);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<SystemClientCO> getSystemClientInfo(Integer id) {
        SystemClientDO systemClient = systemClientMapper.getClientById(id);
        if ( null != systemClient) {
            return SingleResponse.of(SystemClientCoConvertor.toClientObject(systemClient));
        }
        return SingleResponse.of(new SystemClientCO());
    }

    @Override
    public MultiResponse<SystemClientCO> queryClientBySort(SystemClientSortQuery sortQuery) {
        SystemClientDO querySortQuery = new SystemClientDO();
        BeanUtils.copyProperties(sortQuery,querySortQuery);
        List<SystemClientDO> dataCollection = systemClientMapper.selectClientBySort(querySortQuery);
        return MultiResponse.of(SystemClientCoConvertor.toClientObjectArray(dataCollection));
    }

    @Override
    public PageResponse<SystemClientCO> queryClientPageBySort(SystemClientSortQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        SystemClientDO querySortQuery = new SystemClientDO();
        BeanUtils.copyProperties(pageQuery,querySortQuery);
        List<SystemClientDO> sortedTemplates = systemClientMapper.selectClientBySort(querySortQuery);
        PageInfo<SystemClientDO> pageInfo = new PageInfo<>(sortedTemplates);
        List<SystemClientCO> result = SystemClientCoConvertor.toClientObjectArray(sortedTemplates);
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }
}
