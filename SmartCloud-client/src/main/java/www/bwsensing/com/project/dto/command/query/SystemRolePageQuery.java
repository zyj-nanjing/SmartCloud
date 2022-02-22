package www.bwsensing.com.project.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;
/**
 * @author macos-zyj
 */
@Data
public class SystemRolePageQuery extends PageQuery {
    /**主要层级的权限编码**/
    private String baseRoleCode;
    /**权限编码**/
    private String roleCode;
    /**角色名称**/
    private String roleName;
    /**是否是根权限**/
    private Boolean isBaseRole;
}
