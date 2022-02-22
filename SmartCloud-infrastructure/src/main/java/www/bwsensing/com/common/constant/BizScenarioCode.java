package www.bwsensing.com.common.constant;

import www.bwsensing.com.domain.device.model.alert.NotificationMethod;

/**
 * 业务编号配置
 * 注：BIZ_ID 总业务线 USER_CAUSE 业务用例 scenario 用例场景
 * @author macos-zyj
 */
public class BizScenarioCode {
    public static final String BIZ_ID_CLOUD = "SMART_CLOUD";
    public static final String USE_CAUSE_REGISTER ="REGISTER";
    public static final String USE_CAUSE_UPDATE ="UPDATE";
    public static final String USER_CAUSE_NOTIFICATION ="NOTIFICATION";
    public static final String USER_CAUSE_ANALYSE = "ANALYSE";
    public static final String SYSTEM_CLIENT = "SYSTEM_CLIENT";
    public static final String MANAGER_SCENARIO = "MANAGER";
    public static final String USER_SCENARIO = "USER";
    public static final String ROOT_MANAGER_SCENARIO = "ROOT_MANAGER";
    public static final String GROUP_MANAGER_SCENARIO = "GROUP_MANAGER";
    public static final String BATCH_MAIL_SCENARIO = "BATCH_MAIL";
    public static final String BATCH_SMS_SCENARIO = "BATCH_SMS";
    public static final String BATCH_SCENARIO = "NULL";
    public static final String ANALYSE_BW_ANGLE ="ANGLE_SENSOR";
    public static final String UPDATE_DATA_ORDER = "LRU_UPDATE";

    public static String getOperateRegisterScenario(String role){
        if(RoleConstant.GROUP_ADMIN.equals(role)){
            return GROUP_MANAGER_SCENARIO;
        }else if(RoleConstant.ROOT_ADMIN.equals(role)){
            return ROOT_MANAGER_SCENARIO;
        }else {
            return USER_SCENARIO;
        }
    }


    public static String getNotification(NotificationMethod method){
        switch(method){
            case EMAIL:
                return BATCH_MAIL_SCENARIO;
            case SMS:
                return BATCH_SMS_SCENARIO;
            default:
                return BATCH_SCENARIO;
        }
    }
}
