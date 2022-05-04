package www.bwsensing.com.domain.device.model.alert;

import lombok.Data;
import java.util.Date;
import java.util.List;

import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.device.model.ProductModel;
import www.bwsensing.com.domain.system.model.user.OperateGroup;
/**
 * 告警通知
 * @author macos-zyj
 */
@Data
public class AlertNotification {
    private static final Integer INFO_LENGTH = 4;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 预警分组编号
     */
    private Integer alertGroupId;
    /**
     * 预警分组名称
     */
    private String alertGroupName;
    /**
     * 业务组编号
     */
    private Integer groupId;
    /**
     * 业务组名称
     */
    private String groupName;
    /**
     * 告警名称
     */
    private String alertName;
    /**
     * 规则名称
     */
    private String roleName;
    /**
     * 传感器编号
     */
    private Integer sensorId;
    /**
     * 传感器Sn编码
     */
    private String sn;
    /**
     * 项目名称
     */
    private  String projectName;
    /**
     * 传感器名称
     */
    private String sensorName;
    /**
     * 传感器型号编号
     */
    private Integer modelId;
    /**
     * 传感器型号
     */
    private String sensorModel;
    /**
     * 告警时间
     */
    private Date alertTime;
    /**
     * 告警信息
     */
    private String summary;
    /**
     * 是否被处理
     */
    private Boolean isHandle;
    /**
     * 提示颜色
     */
    private String color;
    /**
     * 发送方式
     */
    private List<Integer> pushMethods;


    public AlertNotification() {

    }

    public AlertNotification(String info, String summary, Date alertTime) {
        this.analyseNotification(info, summary, alertTime);
    }

    public void analyseNotification(String info, String summary, Date alertTime){
        this.summary = summary;
        this.alertTime= alertTime;
        String[] infoArray = info.split("\\|");
        try {
            if(infoArray.length == INFO_LENGTH) {
                this.sn =infoArray[0];
                this.groupId = Integer.parseInt(infoArray[1]);
                this.roleName = infoArray[2];
                this.alertGroupId = Integer.parseInt(infoArray[3]);
            }else {
                throw new BizException("NOTIFICATION_FORMAT_ERROR","");
            }
        } catch (BizException | NumberFormatException biz){
            throw new BizException("NOTIFICATION_FORMAT_ERROR","通知消息格式有误");
        }
        this.isHandle = false;
    }

    public void initSensorInfo(ProductDevice sensorInfo){
        this.sn = sensorInfo.getUniqueCode();
        this.sensorId = sensorInfo.getId();
        this.sensorName = sensorInfo.getName();
        this.modelId = sensorInfo.getModelId();
    }

    public void initSensorModel(ProductModel model){
        this.sensorModel = model.getProductName();
        this.modelId = model.getId();
    }

    public void initAlertRoleInfo(AlertRole alertRole){
        this.alertName = alertRole.getAlertName();
        this.roleName = alertRole.getName();
        this.color = alertRole.getColor();
    }
    public void initGroupInfo(OperateGroup operateGroup){
        this.groupId = operateGroup.getId();
        this.groupName = operateGroup.getGroupName();
    }
}
