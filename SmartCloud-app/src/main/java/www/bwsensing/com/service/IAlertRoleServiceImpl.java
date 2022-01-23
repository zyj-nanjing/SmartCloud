package www.bwsensing.com.service;

import javax.annotation.Resource;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.AlertRoleService;
import www.bwsensing.com.command.AlertRoleAddCmdExo;
import www.bwsensing.com.command.AlertRoleBindCmdExo;
import www.bwsensing.com.convertor.AlertRoleCoConvertor;
import www.bwsensing.com.domain.device.alert.AlertRole;
import www.bwsensing.com.domain.gateway.AlertRoleGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.dto.command.AlertRoleBindCmd;
import www.bwsensing.com.dto.command.AlertRoleUpdateCmd;
import www.bwsensing.com.dto.clientobject.AlertRoleCO;
import www.bwsensing.com.dto.command.query.AlertRoleQuery;
import www.bwsensing.com.gatewayimpl.database.AlertRoleMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertRoleDO;
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
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        AlertRoleDO query = new AlertRoleDO();
        BeanUtils.copyProperties(pageQuery,query);
        query.setOperateGroupId(tokenGateway.getTokenInfo().getGroupId());
        query.setAlertGroupId(pageQuery.getAlertGroupId());
        List<AlertRoleDO> resultList = alertRoleMapper.selectAlertRoleBySort(query);
        PageInfo<AlertRoleDO> pageInfo = new PageInfo<>(resultList);
        List<AlertRoleCO> result = AlertRoleCoConvertor.toClientCollection(resultList);
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex() );
    }

    @Override
    public Response deleteAlertRole(Integer roleId) {
        alertRoleGateway.deleteAlertRole(roleId);
        return Response.buildSuccess();
    }
}
