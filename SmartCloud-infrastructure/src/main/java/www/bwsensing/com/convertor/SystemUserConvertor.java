package www.bwsensing.com.convertor;

import www.bwsensing.com.domain.system.SystemUser;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemUserDO;

/**
 * @author macos-zyj
 */
public class SystemUserConvertor {
    public static SystemUser toDomain(SystemUserDO dataObject){
        SystemUser user = new SystemUser();
        user.setId(dataObject.getId());
        user.setAccountName(dataObject.getAccountName());
        user.setUserName(dataObject.getUserName());
        user.setWorkNumber(dataObject.getWorkNumber());
        user.setPassword(dataObject.getPassword());
        user.setAvatar(dataObject.getAvatar());
        user.setMobile(dataObject.getMobile());
        user.setGroupId(dataObject.getOperateGroupId());
        user.setRole(dataObject.getRole());
        user.setEnabled(dataObject.getEnabled());
        user.setAccountNonLocked(dataObject.getAccountNonLocked());
        user.setLastLoginTime(dataObject.getLastLoginTime());
        return user;
    }

    public static SystemUserDO toDataObject(SystemUser sysUser){
        SystemUserDO dataObject = new SystemUserDO();
        dataObject.setId(sysUser.getId());
        dataObject.setAccountName(sysUser.getAccountName());
        dataObject.setUserName(sysUser.getUserName());
        dataObject.setWorkNumber(sysUser.getWorkNumber());
        dataObject.setPassword(sysUser.getPassword());
        dataObject.setRole(sysUser.getRole());
        dataObject.setMobile(sysUser.getMobile());
        dataObject.setAvatar(sysUser.getAvatar());
        dataObject.setEnabled(sysUser.isEnabled());
        dataObject.setOperateGroupId(sysUser.getGroupId());
        dataObject.setAccountNonLocked(sysUser.isAccountNonLocked());
        dataObject.setLastLoginTime(sysUser.getLastLoginTime());
        return dataObject;
    }
}
