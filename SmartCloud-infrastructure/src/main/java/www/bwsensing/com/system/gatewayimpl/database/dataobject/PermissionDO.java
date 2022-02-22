package www.bwsensing.com.system.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class PermissionDO {
    /**
     * 权限编号
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限Code
     */
    private String code;;

    /**
     * 授权url
     */
    private String url;

    /**
     * 描述
     *
     */
    private String comment;

    /**
     * 权限状态,0正常，-1删除
     */
    private Boolean status;
}
