package www.bwsensing.com.system.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.system.dto.command.OperateGroupSaveCmd;
import www.bwsensing.com.system.dto.command.OperateGroupUpdateCmd;
import www.bwsensing.com.system.dto.clientobject.OperateGroupCO;
import www.bwsensing.com.common.clientobject.TreeCO;

/**
 * @author macos-zyj
 */
@Deprecated
public interface OperateGroupService {
    /**
     * 保存
     * @param saveCmd
     * @return
     */
    Response saveGroup(OperateGroupSaveCmd saveCmd);

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response  updated(OperateGroupUpdateCmd updateCmd);

    /**
     * 删除
     * @param groupId
     * @return
     */
    Response deleteGroup(Integer groupId);
    /**
     * 获取分组结构以及用户信息
     * @return
     */
    MultiResponse<OperateGroupCO> showGroupTree();


    /**
     * 按照权限获取分组树
     * @return
     */
    MultiResponse<OperateGroupCO> showGroupTreeByRole();

    /**
     * 小组树
     * @return
     */
    MultiResponse<TreeCO> showGroupTreeCoByRole();
}
