package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.command.AlarmTemplateSaveCmd;
import www.bwsensing.com.device.dto.command.AlarmTemplateUpdateCmd;
import www.bwsensing.com.monitor.dto.clientobject.AlertTemplateCO;
import www.bwsensing.com.device.dto.command.query.AlertTemplateQuery;


/**
 * 预警模板
 * @author macos-zyj
 */
public interface TemplateService {
    /**
     * 保存监测模板
     * @param saveCmd
     * @return
     */
    Response saveTemplate(AlarmTemplateSaveCmd saveCmd);


    /**
     * 修改监测模板
     * @param updateCmd
     * @return
     */
    Response updateTemplate(AlarmTemplateUpdateCmd updateCmd);


    /**
     * 分页组合查询
     * @param pageQuery
     * @return
     */
    PageResponse<AlertTemplateCO> selectTemplateBySort(AlertTemplateQuery pageQuery);

    /**
     * Id查询
     * @param templateId
     * @return
     */
    SingleResponse<AlertTemplateCO> selectAlertTemplateById(Integer templateId);

}
