package www.bwsensing.com.device.command;

import com.alibaba.cola.dto.Response;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.dto.command.SensorUpdateCmd;
import www.bwsensing.com.device.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorDO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class SensorUpdateCmdExo {
    @Resource
    private SensorMapper sensorMapper;

    public Response execute(SensorUpdateCmd updateCmd){
        SensorDO updateData = new SensorDO();
        updateData.setId(updateCmd.getId());
        updateData.setName(updateCmd.getName());
        updateData.setLatitude(updateCmd.getLatitude());
        updateData.setLongitude(updateCmd.getLongitude());
        updateData.setPhoneNumber(updateCmd.getPhoneNumber());
        sensorMapper.update(updateData);
        return Response.buildSuccess();
    }
}