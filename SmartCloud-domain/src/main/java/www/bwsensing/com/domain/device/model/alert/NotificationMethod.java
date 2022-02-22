package www.bwsensing.com.domain.device.model.alert;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */

public enum NotificationMethod {
    /**
     * 邮件
     */
    EMAIL("邮件",1),
    /**
     * 短信
     */
    SMS("短信",2);
    private final String methodName;
    private final Integer typeId;

    NotificationMethod(String methodName, Integer typeId) {
        this.methodName = methodName;
        this.typeId = typeId;
    }
    public Integer getTypeId() {
        return typeId;
    }

    public String getMethodName() {
        return methodName;
    }

    public static NotificationMethod getNotificationMethod(Integer pushMethod){
        for (NotificationMethod method:values()){
            if (method.getTypeId().equals(pushMethod)){
                return method;
            }
        }
        throw new BizException("BIZ_NOTIFICATION_METHOD_ERROR","消息通知方式编码不存在!");
    }
}
