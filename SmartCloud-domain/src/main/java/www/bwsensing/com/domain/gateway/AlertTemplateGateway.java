package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.device.alert.AlertTemplate;
/**
 * @author macos-zyj
 */
public interface AlertTemplateGateway {
    /**
     * 保存
     * @param alarmTemplate
     */
    void save(AlertTemplate alarmTemplate);

    /**
     * 修改
     * @param alarmTemplate
     */
    void update(AlertTemplate alarmTemplate);

    /**
     * 根据模板编号获取预警信息并鉴权
     * @param templateId
     * @return
     */
    AlertTemplate getAlertTemplateById(Integer templateId);
}
