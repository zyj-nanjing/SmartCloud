package www.bwsensing.com.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.command.SensorSaveCmd;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;

import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class SensorSaveCmdExo {
    @Resource
    private SensorMapper sensorMapper;
    @Resource
    private TokenGateway tokenGateway;

    public Response execute(SensorSaveCmd saveCmd){
        if(null != sensorMapper.selectSensorBySn(saveCmd.getSn())){
            throw new BizException("CURRENT_SENSOR_SN_EXIST","当前传感器SN码已存在");
        }
        TokenData tokenData = tokenGateway.getTokenInfo();
        SensorDO saveData = new SensorDO();
        saveData.setSn(saveCmd.getSn());
        saveData.setName(saveCmd.getName());
        saveData.setModelId(saveCmd.getModelId());
        saveData.setManagerId(tokenData.getUserId());
        saveData.setMemberGroupId(tokenData.getGroupId());
        saveData.setLatitude(saveCmd.getLatitude());
        saveData.setLongitude(saveCmd.getLongitude());
        saveData.setPhoneNumber(saveCmd.getPhoneNumber());
        sensorMapper.save(saveData);
        return Response.buildSuccess();
    }
}
