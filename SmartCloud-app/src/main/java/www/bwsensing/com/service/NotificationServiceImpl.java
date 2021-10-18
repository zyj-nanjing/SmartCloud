package www.bwsensing.com.service;

import javax.annotation.Resource;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.exception.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.INotificationService;
import www.bwsensing.com.convertor.NotificationCoConvertor;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.dto.clientobject.NotificationCO;
import www.bwsensing.com.dto.command.query.NotificationQuery;
import www.bwsensing.com.gatewayimpl.database.AlertNotificationMapper;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertNotificationDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;

import java.util.List;


/**
 * @author macos-zyj
 */
@Component
public class NotificationServiceImpl implements INotificationService {
    @Resource
    private AlertNotificationMapper notificationMapper;
    @Resource
    private SensorMapper sensorMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Override
    public PageResponse<NotificationCO> selectNotificationByPage(NotificationQuery pageQuery) {
        AlertNotificationDO query = new AlertNotificationDO();
        SensorDO sensorInfo = sensorMapper.selectSensorBySn(pageQuery.getCurrentDevice());
        Assert.notNull(sensorInfo,"SENSOR_CODE_NOT_TRUE","设备码不存在!");
        query.setGroupId(tokenGateway.getTokenInfo().getGroupId());
        query.setSensorId(sensorInfo.getId());
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        List<AlertNotificationDO> resultList = notificationMapper.selectNotificationBySort(query);
        PageInfo<AlertNotificationDO> pageInfo = new PageInfo<>(resultList);
        List<NotificationCO> result = NotificationCoConvertor.toClientCollection(pageInfo.getList());
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex() );
    }
}
