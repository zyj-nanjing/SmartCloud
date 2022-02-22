package www.bwsensing.com.system.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.system.dto.clientobject.SystemClientCO;
import www.bwsensing.com.system.dto.command.SystemClientSaveCmd;
import www.bwsensing.com.system.dto.command.SystemClientUpdateCmd;
import www.bwsensing.com.project.dto.command.query.SystemClientSortQuery;

/**
 * @author macos-zyj
 */
public interface SystemClientService {
    /**
     * 新增客户信息
     * @param saveCmd
     * @return
     */
    Response addSystemClient(SystemClientSaveCmd saveCmd);

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updateSystemClient(SystemClientUpdateCmd updateCmd);

    /**
     * 获取信息
     * @param id
     * @return
     */
    SingleResponse<SystemClientCO> getSystemClientInfo(Integer id);

    /**
     * 非分页条件查询
     * @param sortQuery
     * @return
     */
    MultiResponse<SystemClientCO> queryClientBySort(SystemClientSortQuery sortQuery);


    /**
     * 页条件查询
     * @param pageQuery
     * @return
     */
    PageResponse<SystemClientCO> queryClientPageBySort(SystemClientSortQuery pageQuery);
}
