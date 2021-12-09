package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.dto.clientobject.OperateGroupCO;
import www.bwsensing.com.dto.clientobject.TreeCO;
import www.bwsensing.com.dto.command.OperateGroupSaveCmd;
import www.bwsensing.com.dto.command.OperateGroupUpdateCmd;

/**
 * @author macos-zyj
 */
public interface IOperateGroupService {
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
