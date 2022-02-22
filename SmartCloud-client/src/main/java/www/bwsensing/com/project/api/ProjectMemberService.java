package www.bwsensing.com.project.api;

/**
 * @author macos-zyj
 */
public interface ProjectMemberService {
    /**
     * 获取当前项目的权限编码
     * @param projectId
     * @return
     */
    String getCurrentProjectAuthCode(Integer projectId);
}
