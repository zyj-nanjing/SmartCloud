package www.bwsensing.com.service;


import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.ITemplateService;
import www.bwsensing.com.command.AlertTemplateSaveCmdExo;
import www.bwsensing.com.command.AlertTemplateUpdateCmdExo;
import www.bwsensing.com.convertor.AlertTemplateCoConvertor;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.dto.clientobject.AlertTemplateCO;
import www.bwsensing.com.dto.command.AlarmTemplateSaveCmd;
import www.bwsensing.com.dto.command.AlarmTemplateUpdateCmd;
import www.bwsensing.com.dto.command.query.AlertTemplateQuery;
import www.bwsensing.com.gatewayimpl.database.AlertTemplateMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertTemplateDO;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class TemplateServiceImpl implements ITemplateService {
    @Resource
    private AlertTemplateSaveCmdExo saveCmdExo;
    @Resource
    private AlertTemplateUpdateCmdExo updateCmdExo;
    @Resource
    private AlertTemplateMapper templateMapper;
    @Resource
    private TokenGateway tokenGateway;
    @Override
    public Response saveTemplate(AlarmTemplateSaveCmd saveCmd) {
        return saveCmdExo.execute(saveCmd);
    }

    @Override
    public Response updateTemplate(AlarmTemplateUpdateCmd updateCmd) {
        return updateCmdExo.execute(updateCmd);
    }

    @Override
    public PageResponse<AlertTemplateCO> selectTemplateBySort(AlertTemplateQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        AlertTemplateDO query = new AlertTemplateDO();
        BeanUtils.copyProperties(pageQuery,query);
        query.setGroupId(tokenGateway.getTokenInfo().getGroupId());
        List<AlertTemplateDO> resultList = templateMapper.selectTemplatesBySort(query);
        PageInfo<AlertTemplateDO> pageInfo = new PageInfo<>(resultList);
        List<AlertTemplateCO> result = AlertTemplateCoConvertor.toClientObjectList(resultList);
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex() );
    }

    @Override
    public SingleResponse<AlertTemplateCO> selectAlertTemplateById(Integer templateId) {
        AlertTemplateDO result = templateMapper.selectTemplateById(templateId);
        if (null != result){
            return SingleResponse.of(AlertTemplateCoConvertor.toClientObject(result));
        }
        return SingleResponse.of(new AlertTemplateCO());
    }

}
