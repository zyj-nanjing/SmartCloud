package www.bwsensing.com.system.convertor;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.system.dto.clientobject.UserInfoCO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemUserDO;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class UserDataCoConvertor {
    private static final BeanCopier USER_INFO_COPY = BeanCopier.create(SystemUser.class, UserInfoCO.class,false);

    public static UserInfoCO toClientObject(SystemUserDO userData){
        UserInfoCO infoCo = new UserInfoCO();
        BeanUtils.copyProperties(userData,infoCo);
        infoCo.setGroupId(userData.getOperateGroupId());
        return infoCo;
    }
    public static UserInfoCO domainToClientObject(SystemUser systemUser){
        UserInfoCO userInfo = new UserInfoCO();
        USER_INFO_COPY.copy(systemUser,userInfo,null);
        return userInfo;
    }

    public static List<UserInfoCO> toClientList(List<SystemUserDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(UserDataCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
