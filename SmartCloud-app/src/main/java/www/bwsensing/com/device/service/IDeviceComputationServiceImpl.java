package www.bwsensing.com.device.service;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.common.utills.bean.BeanUtils;
import www.bwsensing.com.device.api.DeviceComputationService;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.convertor.DeviceComputationCoConvertor;
import www.bwsensing.com.device.convertor.DeviceComputationConvertor;
import www.bwsensing.com.device.convertor.FacilityReceiveCoConvertor;
import www.bwsensing.com.device.dto.clientobject.DeviceComputationCO;
import www.bwsensing.com.device.dto.clientobject.FacilityReceiveCO;
import www.bwsensing.com.device.dto.command.query.FacilityReceivePageQuery;
import www.bwsensing.com.device.gatewayimpl.database.DeviceComputationMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.MonitorReceiveDO;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.device.dto.command.DeviceComputationSaveCmd;
import www.bwsensing.com.device.dto.command.DeviceComputationUpdateCmd;
import www.bwsensing.com.device.dto.command.query.DeviceComputationPageQuery;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class IDeviceComputationServiceImpl implements DeviceComputationService {

    @Autowired
    private ProductDeviceGateway productDeviceGateway;
    @Resource
    private DeviceComputationMapper deviceComputationMapper;

    @Override
    public Response addDeviceComputation(DeviceComputationSaveCmd saveCmd) {
        DeviceComputationDO dataObject = new DeviceComputationDO();
        BeanUtils.copyProperties(saveCmd, dataObject);
        productDeviceGateway.addDeviceComputation(DeviceComputationConvertor.toDomain(dataObject));
        return Response.buildSuccess();
    }

    @Override
    public Response updateDeviceComputation(DeviceComputationUpdateCmd updateCmd) {
        DeviceComputationDO dataObject = new DeviceComputationDO();
        BeanUtils.copyProperties(updateCmd, dataObject);
        productDeviceGateway.updateDeviceComputation(DeviceComputationConvertor.toDomain(dataObject));
        return Response.buildSuccess();
    }

    @Override
    public Response deleteDeviceComputation(Integer id) {
        productDeviceGateway.deleteDeviceComputation(id);
        return Response.buildSuccess();
    }

    @Override
    public PageResponse<DeviceComputationCO> getDeviceComputationBySort(DeviceComputationPageQuery pageQuery) {
        PageHelperUtils<DeviceComputationPageQuery, DeviceComputationDO> pageHelper =
                PageHelperUtils.<DeviceComputationPageQuery, DeviceComputationDO>builder()
                        .pageFunction((groupQuery)->deviceComputationMapper.queryDeviceComputationBySort(initializeQuery(groupQuery))).build();
        PageInfo<DeviceComputationDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<DeviceComputationCO> result = DeviceComputationCoConvertor.toClientCollections(pageInfo.getList());
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public SingleResponse<DeviceComputationCO> getDeviceComputationById(Integer id) {
        DeviceComputationDO result = deviceComputationMapper.getDeviceComputationById(id);
        if ( null != result){
            return SingleResponse.of(DeviceComputationCoConvertor.toClientObject(result));
        }
        throw new BizException("DEVICE_COMPUTATION_NOT_FOUND","设备与计算模型关联不存在!");
    }

    private DeviceComputationDO initializeQuery(DeviceComputationPageQuery pageQuery){
        DeviceComputationDO query = new DeviceComputationDO();
        BeanUtils.copyProperties(pageQuery, query);
        return query;
    }
}
