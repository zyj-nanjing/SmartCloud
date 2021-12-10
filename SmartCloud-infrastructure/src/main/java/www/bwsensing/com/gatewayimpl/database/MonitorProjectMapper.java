package www.bwsensing.com.gatewayimpl.database;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorProjectDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.ProjectMemberDO;

/**
 * @author macos-zyj
 */
public interface MonitorProjectMapper {
    /**
     * 保存
     * @param project
     */
    void save(MonitorProjectDO project);

    /**
     * 修改
     * @param project
     */
    void update(MonitorProjectDO project);

    /**
     * 保存人员
     * @param member
     */
    void saveMember(ProjectMemberDO member);

    /**
     * 修改人员
     * @param member
     */
    void updateMember(ProjectMemberDO member);

    /**
     * 删除项目成员
     * @param pid
     * @param mid
     */
    void deleteMember(@Param("pid")Integer pid,@Param("mid")Integer mid);

    /**
     * 根据项目编号删除所有人员
     * @param projectId
     */
    void deleteMemberByProjectId(Integer projectId);
    /**
     * 删除
     * @param projectId
     */
    void deleteProject(Integer projectId);
    /**
     * 根据用户编号进行删除
     * @param userId
     */
    void deleteMemberByUserId(Integer userId);
    /**
     * 根据编号获取项目
     * @param id
     * @return
     */
    MonitorProjectDO selectMonitorProjectById(Integer id);

    /**
     * 获取获取项目用户
     * @param projectId 项目编号
     * @param userId 用户编号
     * @return
     */
    ProjectMemberDO selectMemberByUserId(@Param("projectId") Integer projectId,@Param("userId") Integer userId);

    /**
     * 计算当前用户所拥有的项目
     * @param userId
     * @return
     */
    Integer countUserOwner(Integer userId);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    ProjectMemberDO selectMemberById(Integer id);
    /**
     * 获取当前项目下所有的成员
     * @param projectId
     * @return
     */
    List<ProjectMemberDO> selectProjectNumbersByPid(Integer projectId);

    /**
     * 根据用户编号获取监测项目
     * @param userId
     * @return
     */
    List<MonitorProjectDO> selectMonitorProject(Integer userId);

    /**
     * 获取当前用户编号对应的项目权限
     * @param projectId 项目编号
     * @param userId 用户编号
     * @return
     */
    String getProjectRoleByUserId(@Param("projectId") Integer projectId,@Param("userId") Integer userId);

}
