package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.command.PositionBindCmd;
import www.bwsensing.com.dto.command.ProjectMemberDeleteCmd;
import www.bwsensing.com.dto.command.ProjectMemberStorageCmd;
import www.bwsensing.com.dto.command.ProjectSaveCmd;
import www.bwsensing.com.dto.clientobject.*;
import www.bwsensing.com.dto.command.query.BaseQuery;

/**
 * @author macos-zyj
 */
public interface IProjectService {
    /**
     * 保存
     * @param saveCmd
     * @return
     */
    SingleResponse<Integer> saveProject(ProjectSaveCmd saveCmd);

    /**
     * 添加当前项目中的人员
     * @param memberSaveCmd
     * @return
     */
    Response storageProjectMember(ProjectMemberStorageCmd memberSaveCmd);

    /**
     * 获取传感器树
     * @return
     */
    MultiResponse<TreeCO> showProjectSensorTree();
    /**
     * 删除人员
     * @param deleteCmd
     * @return
     */
    Response deleteMember(ProjectMemberDeleteCmd deleteCmd);

    /**
     * 删除项目
     * @param projectId
     * @return
     */
    Response deleteProject(Integer projectId);
    /**
     * 同步结构物模型
     * @param modelId
     * @return
     */
    Response synchroStructureModel(Integer modelId);
    /**
     * 绑定测点
     * @param bindCmd
     * @return
     */
    Response bindPosition(PositionBindCmd bindCmd);

    /**
     * 查询项目
     * @return
     */
    MultiResponse<ProjectCO> selectProjectByPermission();

    /**
     * 根据项目编号查询测点
     * @param projectId
     * @return
     */
    MultiResponse<ProjectPositionCO> queryProjectPosition(Integer projectId);

    /**
     * 项目分页
     * @param pageQuery
     * @return
     */
    PageResponse<ProjectCO> projectPageQuery(BaseQuery pageQuery);
    /**
     * 获取同属于当前项目下同小组的人员信息
     * @param projectId
     * @return
     */
    MultiResponse<UserInfoCO> queryCurrentGroupUsers(Integer projectId);
    /**
     * 根据编号获取项目
     * @param projectId
     * @return
     */
    SingleResponse<ProjectCO> projectPathQuery(Integer projectId);

    /**
     * 根据项目编号获取项目成员集合
     * @param projectId
     * @return
     */
    MultiResponse<ProjectMemberCO> selectProjectMembers(Integer projectId);
}
