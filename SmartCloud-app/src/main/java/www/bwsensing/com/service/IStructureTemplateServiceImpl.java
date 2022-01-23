package www.bwsensing.com.service;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import www.bwsensing.com.api.StructureTemplateService;
import www.bwsensing.com.domain.gateway.IndustryFieldGateway;
import www.bwsensing.com.dto.clientobject.StructureTemplateCO;
import www.bwsensing.com.dto.command.StructureTemplateSaveCmd;
import www.bwsensing.com.dto.command.StructureTemplateUpdateCmd;
import www.bwsensing.com.convertor.StructureTemplateCoConvertor;
import www.bwsensing.com.domain.gateway.SysStructureTemplateGateway;
import www.bwsensing.com.domain.system.organization.StructureTemplate;
import www.bwsensing.com.dto.command.query.StructureTemplateSortQuery;
import www.bwsensing.com.gatewayimpl.database.StructureTemplateMapper;
import www.bwsensing.com.dto.command.query.StructureTemplateSortPageQuery;
import www.bwsensing.com.gatewayimpl.database.dataobject.SysStructureTemplateDO;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class IStructureTemplateServiceImpl implements StructureTemplateService {
    @Resource
    private SysStructureTemplateGateway templateGateway;
    @Resource
    private IndustryFieldGateway indusFieldGateway;
    @Resource
    private StructureTemplateMapper structureTemplateMapper;

    @Override
    public Response addStructureTemplate(StructureTemplateSaveCmd saveCmd) {
        StructureTemplate savedTemplate = new StructureTemplate();
        BeanUtils.copyProperties(saveCmd, savedTemplate);
        savedTemplate.setBelowField(indusFieldGateway.getIndustryFieldsByIds(saveCmd.getBelowFields()));
        templateGateway.save(savedTemplate);
        return Response.buildSuccess();
    }

    @Override
    public Response updateStructureTemplate(StructureTemplateUpdateCmd updateCmd) {
        StructureTemplate savedTemplate = new StructureTemplate();
        BeanUtils.copyProperties(updateCmd, savedTemplate);
        savedTemplate.setBelowField(indusFieldGateway.getIndustryFieldsByIds(updateCmd.getBelowFields()));
        templateGateway.update(savedTemplate);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<StructureTemplateCO> getStructureTemplateById(Integer id) {
        SysStructureTemplateDO dataTemplate = structureTemplateMapper.getStructureTemplateById(id);
        if(null != dataTemplate) {
            return SingleResponse.of(StructureTemplateCoConvertor.toClientObject(dataTemplate));
        }
        return SingleResponse.of(new StructureTemplateCO());
    }

    @Override
    public PageResponse<StructureTemplateCO> pageQueryStructureTemplateBySort(StructureTemplateSortPageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        SysStructureTemplateDO querySortQuery = new SysStructureTemplateDO();
        BeanUtils.copyProperties(pageQuery,querySortQuery);
        List<SysStructureTemplateDO> sortedTemplates = structureTemplateMapper.queryStructureTemplateBySort(querySortQuery);
        PageInfo<SysStructureTemplateDO> pageInfo = new PageInfo<>(sortedTemplates);
        List<StructureTemplateCO> result = StructureTemplateCoConvertor.toClientObjectArray(sortedTemplates);
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public MultiResponse<StructureTemplateCO> queryStructureTemplateBySort(StructureTemplateSortQuery templateSortQuery) {
        SysStructureTemplateDO querySortQuery = new SysStructureTemplateDO();
        BeanUtils.copyProperties(templateSortQuery,querySortQuery);
        List<SysStructureTemplateDO> sortedTemplates = structureTemplateMapper.queryStructureTemplateBySort(querySortQuery);
        return MultiResponse.of(StructureTemplateCoConvertor.toClientObjectArray(sortedTemplates));
    }

}
