package www.bwsensing.com.system.service;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.github.pagehelper.PageInfo;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.api.SystemClientService;
import www.bwsensing.com.common.core.lru.EhCacheLruService;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.system.dto.clientobject.SystemClientCO;
import www.bwsensing.com.system.dto.command.SystemClientSaveCmd;
import www.bwsensing.com.system.convertor.SystemClientCoConvertor;
import www.bwsensing.com.domain.system.model.client.SystemClient;
import www.bwsensing.com.system.dto.command.SystemClientUpdateCmd;
import www.bwsensing.com.domain.system.gateway.SystemClientGateway;
import www.bwsensing.com.domain.monitor.gateway.IndustryFieldGateway;
import www.bwsensing.com.system.gatewayimpl.database.SystemClientMapper;
import www.bwsensing.com.project.dto.command.query.SystemClientSortQuery;
import www.bwsensing.com.domain.system.model.organization.SystemStructure;
import www.bwsensing.com.domain.system.gateway.SysStructureTemplateGateway;
import www.bwsensing.com.domain.system.model.organization.StructureTemplate;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemClientDO;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class ISystemClientServiceImpl implements SystemClientService {
    private static final String BIZ_ID = "SYSTEM_CLIENT";
    private static final Integer MAX_LRU_SIZE = 20;
    @Resource
    private SystemClientGateway clientGateway;
    @Resource
    private SysStructureTemplateGateway structureTemplateGateway;
    @Resource
    private IndustryFieldGateway industryFieldGateway;
    @Resource
    private SystemClientMapper systemClientMapper;
    @Resource
    private EhCacheLruService ehCacheLruService;


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
        systemClient.setReleaseFields(industryFieldGateway.getIndustryFieldsByIds(updateCmd.getReleaseFields()));
        clientGateway.updateSystemClient(systemClient);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<SystemClientCO> getSystemClientInfo(Integer id) {
        SystemClientDO systemClient =(SystemClientDO)ehCacheLruService.getCache(BIZ_ID,id+"");
        boolean isCache = true;
        if (null == systemClient){
            systemClient = systemClientMapper.getClientById(id);
            isCache = false;
        }
        if ( null != systemClient) {
            if (!isCache){
                ehCacheLruService.setCache(BIZ_ID,id+"",systemClient,MAX_LRU_SIZE);
            }
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
        PageHelperUtils<SystemClientSortQuery, SystemClientDO> pageHelper =
                PageHelperUtils.<SystemClientSortQuery,SystemClientDO>builder()
                        .pageFunction((groupQuery)->systemClientMapper.selectClientBySort(initializeQuery(pageQuery))).build();
        PageInfo<SystemClientDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<SystemClientCO> result = SystemClientCoConvertor.toClientObjectArray(pageInfo.getList());
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }


    private SystemClientDO initializeQuery(SystemClientSortQuery pageQuery){
        SystemClientDO querySortQuery = new SystemClientDO();
        BeanUtils.copyProperties(pageQuery,querySortQuery);
        return querySortQuery;
    }
}
