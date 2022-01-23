package www.bwsensing.com.domain.device.alert;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.domain.system.user.SystemUser;
import www.bwsensing.com.domain.device.SensorInfo;

/**
 * 告警组 用来做告警规则分组
 * @author macos-zyj
 */
@Data
public class AlertGroup {
    private Integer id;
    /**告警规则分组名称*/
    private String groupName;
    /**小组编号*/
    private Integer operateGroupId;
    /**当前传感器*/
    private SensorInfo currentSensor;
    /**当前模板*/
    private AlertTemplate currentTemplate;
    /**告警规则集合*/
    private List<AlertRole> roleCollection;
    /**
     * 消息推送方式
     */
    private NotificationMethod notificationMethod;
    /**当前用户*/
    private SystemUser currentUser;
    /**创建者*/
    private String creator;
    /**创建时间*/
    private Date createTime;

    public AlertGroup() {

    }

    /**
     * 包含预警模板案例
     * @param groupName 预警分组名称
     * @param currentSensor 当前传感器
     * @param currentTemplate 当前模板
     * @param currentUser 当前用户
     */
    public AlertGroup(String groupName,SensorInfo currentSensor,
                      SystemUser currentUser,AlertTemplate currentTemplate) {
        this.groupName = groupName;
        this.currentSensor = currentSensor;
        this.currentUser = currentUser;
        this.currentTemplate = currentTemplate;
        this.operateGroupId = currentUser.getGroupId();
        this.createTime = new Date();
        initializeRoles(currentTemplate.getAlertParams());
    }

    /**
     * 直接创建方法
     * @param groupName 预警分组名称
     * @param currentSensor 当前传感器
     * @param currentUser 当前用户
     * @param alertParams 当前添加规则
     */
    public AlertGroup(String groupName,SensorInfo currentSensor,
                      SystemUser currentUser,List<AlertParam> alertParams) {
        this.groupName = groupName;
        this.currentSensor = currentSensor;
        this.currentUser = currentUser;
        this.operateGroupId = currentUser.getGroupId();
        this.createTime = new Date();
        initializeRoles(alertParams);
    }

    /**
     * 初始化规则
     * @param alertParams 预警参数
     */
    private void initializeRoles(List<AlertParam> alertParams){
        this.roleCollection = new ArrayList<>();
        if (alertParams.size() > 0){
           alertParams.forEach(current->{
               if (null != this.currentTemplate){
                   roleCollection.add(new AlertRole(this.currentSensor,current,this.currentTemplate));
               } else {
                   roleCollection.add(new AlertRole(this.currentSensor,current));
               }
           });
        } else {
            throw new BizException("ALERT_ROLE_INIT_ERROR","预警规则实例化错误-预警参数不能为空");
        }
    }
}
