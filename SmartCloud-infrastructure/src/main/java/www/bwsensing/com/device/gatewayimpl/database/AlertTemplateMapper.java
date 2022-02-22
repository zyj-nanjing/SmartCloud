package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertTemplateDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface AlertTemplateMapper {

    /**
     * 保存模板
     * @param template
     */
    void saveTemplate(AlertTemplateDO template);

    /**
     * 修改模板
     * @param template
     */
    void updateTemplate(AlertTemplateDO template);


    /**
     * 条件组合查询
     * @param templateSort
     * @return
     */
    List<AlertTemplateDO> selectTemplatesBySort(AlertTemplateDO templateSort);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    AlertTemplateDO selectTemplateById(Integer id);
}
