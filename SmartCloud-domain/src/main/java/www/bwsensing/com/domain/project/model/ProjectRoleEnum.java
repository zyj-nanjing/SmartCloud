package www.bwsensing.com.domain.project.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */
public enum ProjectRoleEnum {
    /**项目拥有者*/
    PROJECT_OWNER("PROJECT_OWNER","项目拥有者"),
    /**管理员*/
    PROJECT_MANAGER("PROJECT_MANAGER","管理员"),
    /**项目参与者*/
    PROJECT_INVOLVED("PROJECT_INVOLVED","项目参与者"),
    /**浏览者*/
    PROJECT_VIEWER("PROJECT_VIEWER","项目浏览者");
    /**角色编码*/
    private final String roleCode;
    /**角色*/
    private final String roleName;

    ProjectRoleEnum(String roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }
    public static ProjectRoleEnum getProjectRoleByCode(String roleCode){
        for (ProjectRoleEnum role : values()){
            if(role.getRoleCode().equals(roleCode)){
                return role;
            }
        }
        throw  new BizException("ROLE_CODE_NOT_EXIST","");
    }

    public static boolean isAllowedMaxRole(String roleCode){
        ProjectRoleEnum roleEnum = getProjectRoleByCode(roleCode);
        switch (roleEnum){
            case PROJECT_OWNER:
            case PROJECT_MANAGER:
                return true;
            default:
                return false;
        }
     }

    public String getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }
}
