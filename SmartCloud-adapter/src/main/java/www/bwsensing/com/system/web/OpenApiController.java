package www.bwsensing.com.system.web;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.common.utills.HexUtils;
import www.bwsensing.com.common.utills.RSAUtils;
import www.bwsensing.com.device.api.ProductDeviceService;
import www.bwsensing.com.common.clientobject.RSAKeyCO;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.system.api.SystemUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.system.dto.command.query.RsaSignValidQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "对外接口")
@RequestMapping("/api/v1.0/open")
@RestController
public class OpenApiController {
    @Autowired
    private SystemUserService userService;

    @Autowired
    private ProductDeviceService productDeviceService;

    @Resource
    private TokenGateway tokenGateway;

    @Resource
    private SystemUserGateway userGateway;

    @ApiOperation("获取秘钥")
    @GetMapping("/rsa/key")
    public SingleResponse<RSAKeyCO> getAccountRsaKey(){
        return userService.getAccountRsaKey();
    }

    @ApiOperation("获取最新设备列表以及当前数据")
    @GetMapping("/sensor/current/data")
    public SingleResponse<String> getCurrentSensorData(){
        return productDeviceService.getProductCurrentData();
    }


    @ApiOperation("签名校验")
    @PostMapping("/sign/valid")
    public Response validateUserSign(@RequestBody RsaSignValidQuery query){
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser systemUser = userGateway.getUserInfoContainRole(tokenData.getUserId());
        byte[] signData = HexUtils.hexStringToBytes(query.getHexData());
        try {
            boolean isValid = RSAUtils.verify(signData,systemUser.getPublicKey(),query.getSign());
            if (isValid ){
                return Response.buildSuccess();
            }
        } catch (Exception  ignore){

        }
        return Response.buildFailure("SIGN NOT VALID","数字签名校验未通过!");
    }


}
