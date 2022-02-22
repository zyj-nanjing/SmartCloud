package www.bwsensing.com.system.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.api.PermissionService;
import www.bwsensing.com.common.command.BaseQuery;
import www.bwsensing.com.system.dto.command.PermissionSaveCmd;
import www.bwsensing.com.system.dto.command.PermissionUpdateCmd;
import www.bwsensing.com.system.dto.clientobject.PermissionCO;
import www.bwsensing.com.system.gatewayimpl.database.PermissionMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class IPermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionCO> selectAllPermission() {
        return null;
    }

    @Override
    public Response savePermission(PermissionSaveCmd saveCmd) {
        return null;
    }

    @Override
    public Response updatePermission(PermissionUpdateCmd updateCmd) {
        return null;
    }

    @Override
    public PageResponse<PermissionCO> selectPermissionPage(BaseQuery baseQuery) {
        return null;
    }

    @Override
    public MultiResponse<PermissionCO> selectMultiPermission() {
        return null;
    }
}
