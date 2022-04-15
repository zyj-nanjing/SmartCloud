package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.command.SensorSaveCmd;
import www.bwsensing.com.device.dto.command.SensorProjectBindCmd;
import www.bwsensing.com.device.dto.clientobject.FacilityReceiveCO;
import www.bwsensing.com.device.dto.command.query.FacilityReceivePageQuery;
import www.bwsensing.com.device.dto.command.query.SensorSortQuery;
import www.bwsensing.com.device.dto.clientobject.SensorBindCO;
import www.bwsensing.com.device.dto.clientobject.SensorMapCO;
import www.bwsensing.com.device.dto.command.SensorUpdateCmd;
import www.bwsensing.com.device.dto.clientobject.SensorCO;
/**
 * @author macos-zyj
 */
public interface SensorService {
    /**
     * 传感器分页查询
     * @param sortQuery
     * @return
     */
    PageResponse<SensorCO> querySensorBySort(SensorSortQuery sortQuery);


    /**
     * 传感器分页查询
     * @param sortQuery
     * @return
     */
    MultiResponse<SensorCO> querySensorAllBySort(SensorSortQuery sortQuery);


    /**
     * 查询数据接收日志
     * @param receivePageQuery
     * @return
     */
    PageResponse<FacilityReceiveCO> queryFacilitySendsBySn(FacilityReceivePageQuery receivePageQuery);


    /**
     * 获取企业下当前传感器数据
     * @return
     */
    SingleResponse<String> getCurrentSensorData();

    /**
     * ID 查询
     * @param id
     * @return
     */
    SingleResponse<SensorCO> querySensorById(Integer id);

    /***
     * 根据项目编号获取传感器集合
     * @param projectId
     * @return
     */
    MultiResponse<SensorCO> selectSensorByProjectId(Integer projectId);
    /**
     * 查询首页传感器信息
     * @return
     */
    MultiResponse<SensorMapCO> showSensorInMap();

    /**
     * 保存传感器
     * @param saveCmd
     * @return
     */
    Response saveSensor(SensorSaveCmd saveCmd);

    /**
     * 修改传感器
     * @param updateCmd
     * @return
     */
    Response updateSensor(SensorUpdateCmd updateCmd);

    /**
     * 关联传感器和项目
     * @param projectBindCmd
     * @return
     */
    Response  bindSensorInProject(SensorProjectBindCmd projectBindCmd);
    /**
     * 根据传感器编号删除
     * @param id
     * @return
     */
    Response deleteSensor(Integer id);

    /**
     * 根据项目编号获取传感器待绑定以及绑定的传感器
     * @param projectId
     * @return
     */
    MultiResponse<SensorBindCO> selectSensorBindArray(Integer projectId);
}
