package www.bwsensing.com.service;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.IndustryFieldService;
import www.bwsensing.com.convertor.IndustryFieldCoConvertor;
import www.bwsensing.com.domain.monitor.industry.IndustryField;
import www.bwsensing.com.dto.clientobject.IndustryFieldCO;
import www.bwsensing.com.dto.command.IndustryFieldSaveCmd;
import www.bwsensing.com.dto.command.IndustryFieldUpdateCmd;
import www.bwsensing.com.domain.gateway.IndustryFieldGateway;
import www.bwsensing.com.dto.command.query.IndustryFileSortQuery;
import www.bwsensing.com.gatewayimpl.database.IndustryFieldMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.IndustryFieldDO;

/**
 * @author macos-zyj
 */
@Component
public class IIndustryFieldServiceImpl implements IndustryFieldService {
    @Resource
    private IndustryFieldGateway industryFieldGateway;
    @Resource
    private IndustryFieldMapper industryFieldMapper;

    @Override
    public Response saveIndustryField(IndustryFieldSaveCmd saveCmd) {
        IndustryField domainObject = new IndustryField();
        BeanUtils.copyProperties(saveCmd, domainObject);
        industryFieldGateway.saveIndustry(domainObject);
        return Response.buildSuccess();
    }

    @Override
    public Response updateIndustryField(IndustryFieldUpdateCmd updateCmd) {
        IndustryField domainObject = new IndustryField();
        BeanUtils.copyProperties(updateCmd, domainObject);
        industryFieldGateway.updateIndustry(domainObject);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<IndustryFieldCO> selectIndustryFieldById(Integer id) {
        IndustryFieldDO dataObject = industryFieldMapper.selectIndustryById(id);
        if(null != dataObject) {
            return SingleResponse.of(IndustryFieldCoConvertor.toClientObject(dataObject));
        }
        return SingleResponse.of(new IndustryFieldCO());
    }

    @Override
    public MultiResponse<IndustryFieldCO> selectIndustryFileQuery(IndustryFileSortQuery sortQuery) {
        IndustryFieldDO querySortQuery = new IndustryFieldDO();
        BeanUtils.copyProperties(sortQuery,querySortQuery);
        List<IndustryFieldDO> dataCollection = industryFieldMapper.selectIndustryBySort(querySortQuery);
        return MultiResponse.of(IndustryFieldCoConvertor.toClientObjectList(dataCollection));
    }


    @Override
    public PageResponse<IndustryFieldCO> selectIndustryFileBySortPage(IndustryFileSortQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        IndustryFieldDO querySortQuery = new IndustryFieldDO();
        BeanUtils.copyProperties(pageQuery,querySortQuery);
        List<IndustryFieldDO> dataCollection = industryFieldMapper.selectIndustryBySort(querySortQuery);
        PageInfo<IndustryFieldDO> pageInfo = new PageInfo<>(dataCollection);
        List<IndustryFieldCO> result = IndustryFieldCoConvertor.toClientObjectList(dataCollection);
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }
}
