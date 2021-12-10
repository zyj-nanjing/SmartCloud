package www.bwsensing.com.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.dto.command.AlertRoleBindCmd;
import www.bwsensing.com.dto.command.AlertRoleUpdateCmd;
import www.bwsensing.com.dto.clientobject.AlertRoleCO;
import www.bwsensing.com.dto.command.query.AlertRoleQuery;

/**
 * @author macos-zyj
 */
public interface IAlertRoleService {
    /**
     * 传感器与监测预警模板绑定
     * @param bindCmd
     * @return
     */
    Response sensorAlertBind(AlertRoleBindCmd bindCmd);

    /**
     * 保存预警规则
     * @param saveCmd
     * @return
     */
    Response saveAlertRole(AlertRoleAddCmd saveCmd);
    /**
     * 修改监测预警模板
     * @param updateCmd
     * @return
     */
    Response updateAlertRole(AlertRoleUpdateCmd updateCmd);

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageResponse<AlertRoleCO> selectAlertRole(AlertRoleQuery query);

    /**
     * 删除
     * @param roleId
     * @return
     */
    Response deleteAlertRole(Integer roleId);
}
