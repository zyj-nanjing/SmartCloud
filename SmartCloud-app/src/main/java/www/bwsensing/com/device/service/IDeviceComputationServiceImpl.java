package www.bwsensing.com.device.service;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.stereotype.Service;
import www.bwsensing.com.common.utills.bean.BeanUtils;
import www.bwsensing.com.device.api.DeviceComputationService;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.convertor.DeviceComputationConvertor;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.device.dto.command.DeviceComputationSaveCmd;
import www.bwsensing.com.device.dto.command.DeviceComputationUpdateCmd;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;
/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class IDeviceComputationServiceImpl implements DeviceComputationService {

    @Autowired
    private ProductDeviceGateway productDeviceGateway;

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
}
