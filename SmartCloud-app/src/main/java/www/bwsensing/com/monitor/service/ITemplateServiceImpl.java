package www.bwsensing.com.monitor.service;


import javax.annotation.Resource;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.api.TemplateService;
import www.bwsensing.com.device.command.AlertTemplateSaveCmdExo;
import www.bwsensing.com.device.command.AlertTemplateUpdateCmdExo;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.convertor.AlertTemplateCoConvertor;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.device.dto.command.AlarmTemplateSaveCmd;
import www.bwsensing.com.device.dto.command.AlarmTemplateUpdateCmd;
import www.bwsensing.com.monitor.dto.clientobject.AlertTemplateCO;
import www.bwsensing.com.device.dto.command.query.AlertTemplateQuery;
import www.bwsensing.com.device.gatewayimpl.database.AlertTemplateMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertTemplateDO;
import java.util.List;


/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class ITemplateServiceImpl implements TemplateService {
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
        PageHelperUtils<AlertTemplateQuery,AlertTemplateDO> pageHelper =
                PageHelperUtils.<AlertTemplateQuery,AlertTemplateDO>builder()
                        .pageFunction((groupQuery)->templateMapper.selectTemplatesBySort (initializeQuery(groupQuery))).build();
        PageInfo<AlertTemplateDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<AlertTemplateCO> result = AlertTemplateCoConvertor.toClientObjectList(pageInfo.getList());
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

    private AlertTemplateDO initializeQuery(AlertTemplateQuery pageQuery){
        AlertTemplateDO query = new AlertTemplateDO();
        BeanUtils.copyProperties(pageQuery,query);
        query.setGroupId(tokenGateway.getTokenInfo().getGroupId());
        return query;
    }
}
