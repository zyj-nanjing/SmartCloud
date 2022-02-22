package www.bwsensing.com.monitor.dto.clientobject;

import java.util.Date;
import java.util.List;
import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import www.bwsensing.com.device.dto.clientobject.AlertParamCO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class AlertTemplateCO extends DTO {
    /**主键*/
    private Integer id;
    /**模型名称*/
    private String modelName;
    /**模型名称*/
    private String templateName;
    /**告警role前缀名称 唯一会校验唯一性*/
    private String namePrefix;
    /**监测参数*/
    private List<AlertParamCO> alertParams;
    /**
     * 告警信息模板   支持占位符 ${item} 检测项名称  ${paramName} 当前数值   ${sensor} 传感器名称  ${alertName} 对应监测相位告警信息 ${paramName}
     * 类似格式 传感器:${sensor} 发生 ${alertName} 问题 当前 ${paramName} 监测查询值为:${paramName}
     **/
    private String summaryModel;
    /**创建者*/
    private String creator;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**修改者*/
    private String updater;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public List<AlertParamCO> getAlertParams() {
        return alertParams;
    }

    public void setAlertParams(List<AlertParamCO> alertParams) {
        this.alertParams = alertParams;
    }

    public String getSummaryModel() {
        return summaryModel;
    }

    public void setSummaryModel(String summaryModel) {
        this.summaryModel = summaryModel;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
