package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.AlertParamDO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface AlertParamMapper {
    /**
     * 保存
     * @param param
     */
    void saveParam(AlertParamDO param);

    /**
     * 修改
     * @param param
     */
    void updateParam(AlertParamDO param);

    /**
     * 根据模板获取预警信息
     * @param templateId
     * @return
     */
    List<AlertParamDO> selectParamByTemplate(Integer templateId);

    /**
     * 根据ID 查询
     * @param id
     * @return
     */
    AlertParamDO selectParamById(Integer id);

    /**
     * 根据模板编号删除
     * @param templateId
     */
    void deleteParamByTempId(Integer templateId);

}
