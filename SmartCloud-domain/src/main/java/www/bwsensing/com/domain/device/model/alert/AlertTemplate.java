package www.bwsensing.com.domain.device.model.alert;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.device.model.ProductModel;

/**
 * 告警模板
 * @author macos-zyj
 */
@Data
public class AlertTemplate {
    /**主键*/
    private Integer id;
    /**模型编号 告警模板对应的设备型号*/
    private Integer modelNo;
    /**
     * 传感器型号
     */
    private ProductModel sensorModel;
    /**业务组编号*/
    private Integer groupId;
    /**模型名称*/
    private String templateName;
    /**告警role前缀名称 唯一会校验唯一性*/
    private String namePrefix;
    /**监测参数*/
    private List<AlertParam> alertParams;
    /**
     * 告警信息模板   支持占位符 ${item} 检测项名称  ${dataNumber} 当前数值   ${sensor} 传感器名称 ${sensorModel} 传感器型号 ${alertName} 对应监测相位告警信息 ${paramName} 检测项
     * 类似格式 传感器:${sensor} 发生 ${alertName} 问题 当前 ${paramName} 监测查询值为:${dataNumber} 传感器型号:${sensorModel}
     **/
    private String summaryModel;
    /**创建者*/
    private String creator;
    private Date createTime;
    /**修改者*/
    private String updater;
    private Date updateTime;



    public void addOrUpdate(String name){
        if(null != id ){
            this.updater = name;
            this.updateTime = new Date();
        } else{
            this.creator = name;
            this.createTime = new Date();
        }
    }

    public List<AlertRole> initializeRole(ProductDevice sensorInfo){
        List<AlertRole> sensorAlertRole = new ArrayList<>();
        this.getAlertParams().forEach(alertParam -> sensorAlertRole.add(new AlertRole(sensorInfo,alertParam,this)));
        return sensorAlertRole;
    }

    public String getDesignSummary(AlertParam altParam, ProductDevice sensor){
        return this.summaryModel.replace("${alertName}",altParam.getAlertName())
                .replace("${dataNumber}","{{$values.data_value}}")
                .replace("${altParam}",altParam.getMonitorItem().getItemName())
                .replace("${sensorModel}",sensor.getSensorModel().getProductName())
                .replace("${sensor}",sensor.getName())
                .replace("${paramName}",altParam.getMonitorItem().getItemName());
    }

}
