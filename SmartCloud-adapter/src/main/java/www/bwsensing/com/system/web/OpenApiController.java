package www.bwsensing.com.system.web;

import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import www.bwsensing.com.device.api.SensorService;
import www.bwsensing.com.common.clientobject.RSAKeyCO;
import www.bwsensing.com.system.api.SystemUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/open")
@RestController
public class OpenApiController {
    @Autowired
    private SystemUserService userService;

    @Autowired
    private SensorService sensorService;

    @ApiOperation("获取秘钥")
    @GetMapping("/rsa/key")
    public SingleResponse<RSAKeyCO> getAccountRsaKey(){
        return userService.getAccountRsaKey();
    }

    @ApiOperation("获取最新涉笔列表以及当前数据")
    @GetMapping("/sensor/current/data")
    public SingleResponse<String> getCurrentSensorData(){
        return sensorService.getCurrentSensorData();
    }
}
