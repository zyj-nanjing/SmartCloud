package www.bwsensing.com.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.INotificationService;
import www.bwsensing.com.common.constant.NotificationConstant;
import www.bwsensing.com.convertor.NotificationCoConvertor;
import www.bwsensing.com.convertor.NotificationMsgCoConvertor;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.clientobject.NotificationCO;
import www.bwsensing.com.dto.clientobject.NotificationMsgCO;
import www.bwsensing.com.dto.command.query.NotificationQuery;
import www.bwsensing.com.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.gatewayimpl.database.AlertNotificationMapper;
import www.bwsensing.com.gatewayimpl.database.CacheNotificationMapper;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertNotificationDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.NotificationTag;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;



/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class NotificationServiceImpl implements INotificationService {

    @Resource
    private AlertGroupMapper alertGroupMapper;
    @Resource
    private SensorMapper sensorMapper;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private CacheNotificationMapper cacheMapper;
    @Resource
    private AlertNotificationMapper alertNotificationMapper;

    @Override
    public PageResponse<NotificationCO> selectNotificationByPage(NotificationQuery pageQuery) {
        AlertNotificationDO query = new AlertNotificationDO();
        SensorDO sensorInfo = sensorMapper.selectSensorBySn(pageQuery.getCurrentDevice());
        Assert.notNull(sensorInfo,"SENSOR_CODE_NOT_TRUE","设备码不存在!");
        query.setGroupId(tokenGateway.getTokenInfo().getGroupId());
        query.setSensorId(sensorInfo.getId());
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        List<AlertNotificationDO> resultList = alertNotificationMapper.selectNotificationBySort(query);
        PageInfo<AlertNotificationDO> pageInfo = new PageInfo<>(resultList);
        List<NotificationCO> result = NotificationCoConvertor.toClientCollection(pageInfo.getList());
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex() );
    }

    @Override
    public void cacheNotification(Integer groupId, String message) {
        NotificationTag saveTag = new NotificationTag();
        saveTag.setGroupId(groupId);
        saveTag.setMessage(message);
        cacheMapper.insertNotificationTag(saveTag);
    }

    @Override
    public MultiResponse<NotificationMsgCO> getCurrentMessage() {
        TokenData tokenData = tokenGateway.getTokenInfo();
        List<AlertGroupDO> groupList = alertGroupMapper.queryAlertGroupByCurrent(tokenData.getUserId());
        List<NotificationTag> resultCollection = new ArrayList<>();
        if (null != groupList && groupList.size() >0){
            for (AlertGroupDO current: groupList) {
                NotificationTag queryObject = new NotificationTag();
                queryObject.setGroupId(current.getId());
                queryObject.setQueryDay(NotificationConstant.MAX_LAST);
                resultCollection.addAll(cacheMapper.queryNotification(queryObject));
            }
        }
        return MultiResponse.of(NotificationMsgCoConvertor.toClientCollection(resultCollection));
    }

    @Override
    public Response updateCacheStatus(List<Integer> updateIds) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        for (Integer updateId : updateIds){
            cacheMapper.updateCacheStatus(tokenData.getUserId(),updateId);
        }
        return Response.buildSuccess();
    }

}
