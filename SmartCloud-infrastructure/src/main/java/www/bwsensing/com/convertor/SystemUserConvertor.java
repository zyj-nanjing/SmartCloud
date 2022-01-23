package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.user.SystemUser;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemUserDO;

/**
 * @author macos-zyj
 */
public class SystemUserConvertor {
    private static final BeanCopier USER_COPIER = BeanCopier.create(SystemUser.class, SystemUserDO.class,false);
    private static final BeanCopier USER_DOMAIN_COPIER = BeanCopier.create(SystemUserDO.class,SystemUser.class,false);

    public static SystemUser toDomain(SystemUserDO dataObject){
        SystemUser user = new SystemUser();
        USER_DOMAIN_COPIER.copy(dataObject,user,null);
        user.setAvatar(dataObject.getAvatar());
        user.setEnabled(dataObject.getEnabled());
        user.setGroupId(dataObject.getOperateGroupId());
        user.setAccountNonLocked(true);
        return user;
    }

    public static SystemUserDO toDataObject(SystemUser sysUser){
        SystemUserDO dataObject = new SystemUserDO();
        USER_COPIER.copy(sysUser,dataObject,null);
        dataObject.setAvatar(sysUser.getAvatar());
        dataObject.setOperateGroupId(sysUser.getGroupId());
        dataObject.setAccountNonLocked(true);
        return dataObject;
    }
}
