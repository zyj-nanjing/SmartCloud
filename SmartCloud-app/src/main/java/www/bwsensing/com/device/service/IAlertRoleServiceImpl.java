package www.bwsensing.com.device.service;

import javax.annotation.Resource;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.github.pagehelper.PageInfo;
import com.alibaba.cola.dto.PageResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import www.bwsensing.com.device.api.AlertRoleService;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.command.AlertRoleAddCmdExo;
import www.bwsensing.com.device.command.AlertRoleBindCmdExo;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.convertor.AlertRoleCoConvertor;
import www.bwsensing.com.domain.device.model.alert.AlertRole;
import www.bwsensing.com.domain.device.gateway.AlertRoleGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.device.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.device.dto.command.AlertRoleBindCmd;
import www.bwsensing.com.device.dto.command.AlertRoleUpdateCmd;
import www.bwsensing.com.device.dto.clientobject.AlertRoleCO;
import www.bwsensing.com.device.dto.command.query.AlertRoleQuery;
import www.bwsensing.com.device.gatewayimpl.database.AlertRoleMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertRoleDO;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class IAlertRoleServiceImpl implements AlertRoleService {
    @Resource
    private AlertRoleBindCmdExo bindCmdExo;
    @Resource
    private AlertRoleAddCmdExo roleAddCmdExo;
    @Resource
    private AlertRoleMapper alertRoleMapper;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private AlertRoleGateway alertRoleGateway;


    @Override
    public Response sensorAlertBind(AlertRoleBindCmd bindCmd) {
        return bindCmdExo.execute(bindCmd);
    }

    @Override
    public Response saveAlertRole(AlertRoleAddCmd saveCmd) {
        return roleAddCmdExo.execute(saveCmd);
    }

    @Override
    public Response updateAlertRole(AlertRoleUpdateCmd updateCmd) {
        AlertRole alertRole = alertRoleGateway.selectAlertRoleById(updateCmd.getId());
        BeanUtil.copyProperties(updateCmd, alertRole, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        alertRoleGateway.updateAlertRole(alertRole);
        return Response.buildSuccess();
    }

    @Override
    public PageResponse<AlertRoleCO> selectAlertRole(AlertRoleQuery pageQuery) {
        PageHelperUtils<AlertRoleQuery, AlertRoleDO> pageHelper =
                PageHelperUtils.<AlertRoleQuery,AlertRoleDO>builder()
                        .pageFunction((groupQuery)->alertRoleMapper.selectAlertRoleBySort (initializeQuery(pageQuery))).build();
        PageInfo<AlertRoleDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<AlertRoleCO> result = AlertRoleCoConvertor.toClientCollection(pageInfo.getList());
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex() );
    }

    @Override
    public SingleResponse<AlertRoleCO> getAlertRoleById(Integer id) {
        AlertRoleDO alertRole = alertRoleMapper.getAlertRoleById(id);
        return SingleResponse.of(AlertRoleCoConvertor.toClientObject(alertRole));
    }

    @Override
    public Response deleteAlertRole(Integer roleId) {
        alertRoleGateway.deleteAlertRole(roleId);
        return Response.buildSuccess();
    }

    private AlertRoleDO initializeQuery(AlertRoleQuery pageQuery){
        AlertRoleDO query = new AlertRoleDO();
        BeanUtils.copyProperties(pageQuery,query);
        query.setOperateGroupId(tokenGateway.getTokenInfo().getGroupId());
        query.setAlertGroupId(pageQuery.getAlertGroupId());
        return query;
    }
}
