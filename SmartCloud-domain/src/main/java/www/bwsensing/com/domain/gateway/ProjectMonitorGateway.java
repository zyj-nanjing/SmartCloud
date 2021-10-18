package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.project.MonitorProject;
import www.bwsensing.com.domain.project.ProjectMember;
import java.util.List;

/**
 * 监测项目
 * @author macos-zyj
 */
public interface ProjectMonitorGateway {
    /**
     * 保存项目
     * @param monitorProject
     * @return
     */
    Integer saveProject(MonitorProject monitorProject);

    /**
     * 根据权限获取当前项目集合
     * @return
     */
    List<MonitorProject> selectProjectByPermission();

    /**
     * 保存项目成员
     * @param member
     */
    void addProjectNumber(ProjectMember member);

    /**
     * 删除项目成员
     * @param member
     */
    void deleteProjectNumber(ProjectMember member);

    /**
     * 修改项目成员
     * @param member
     */
    void updateProjectNumber(ProjectMember member);

    /**
     * 修改项目
     * @param monitorProject
     */
    void updateProject(MonitorProject monitorProject);

    /**
     * 删除
     * @param projectId
     */
    void deleteProject(Integer projectId);
}
