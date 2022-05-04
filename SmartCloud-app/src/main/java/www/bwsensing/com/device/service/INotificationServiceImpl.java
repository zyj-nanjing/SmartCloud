package www.bwsensing.com.device.service;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.github.pagehelper.PageInfo;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.api.NotificationService;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.common.constant.NotificationConstant;
import www.bwsensing.com.device.dto.clientobject.NotificationCO;
import www.bwsensing.com.device.dto.clientobject.NotificationMsgCO;
import www.bwsensing.com.device.dto.command.query.NotificationQuery;
import www.bwsensing.com.project.convertor.NotificationCoConvertor;
import www.bwsensing.com.project.convertor.NotificationMsgCoConvertor;
import www.bwsensing.com.device.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.device.gatewayimpl.database.AlertNotificationMapper;
import www.bwsensing.com.device.gatewayimpl.database.CacheNotificationMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.NotificationTag;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertNotificationDO;


/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class INotificationServiceImpl implements NotificationService {

    @Resource
    private AlertGroupMapper alertGroupMapper;
    @Resource
    private ProductDeviceMapper productDeviceMapper;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private CacheNotificationMapper cacheMapper;
    @Resource
    private AlertNotificationMapper alertNotificationMapper;

    @Override
    public PageResponse<NotificationCO> selectNotificationByPage(NotificationQuery pageQuery) {
        PageHelperUtils<NotificationQuery, AlertNotificationDO> pageHelper =
                PageHelperUtils.<NotificationQuery,AlertNotificationDO>builder()
                        .pageFunction((groupQuery)->alertNotificationMapper.selectNotificationBySort(initializeQuery(pageQuery))).build();
        PageInfo<AlertNotificationDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<NotificationCO> result = NotificationCoConvertor.toClientCollection(pageInfo.getList());
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex() );
    }

    private AlertNotificationDO initializeQuery(NotificationQuery pageQuery){
        AlertNotificationDO query = new AlertNotificationDO();
        ProductDeviceDO sensorInfo = productDeviceMapper.getProductDetailByUniqueCode(pageQuery.getCurrentDevice());
        Assert.notNull(sensorInfo,"SENSOR_CODE_NOT_TRUE","设备码不存在!");
        query.setGroupId(tokenGateway.getTokenInfo().getGroupId());
        query.setSensorId(sensorInfo.getId());
        return query;
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
