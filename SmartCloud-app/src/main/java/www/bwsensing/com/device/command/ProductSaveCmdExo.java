package www.bwsensing.com.device.command;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.device.dto.command.ProductSaveCmd;
import www.bwsensing.com.domain.device.model.ExtraProductDataItem;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;

/**
 * @author macos-zyj
 */
@Component
public class ProductSaveCmdExo {
    @Resource
    private ProductDeviceGateway productDeviceGateway;
    @Resource
    private TokenGateway tokenGateway;

    public Response execute(ProductSaveCmd saveCmd){
        ProductDevice domainObject = new ProductDevice();
        BeanUtils.copyProperties(saveCmd, domainObject);
        TokenData tokenData = tokenGateway.getTokenInfo();
        domainObject.setOrganizationId(tokenData.getGroupId());
        if (null != saveCmd.getExtraData()){
            List<ExtraProductDataItem> extraDataItems = new ArrayList<>();
            saveCmd.getExtraData().forEach((key,value) -> extraDataItems.add(new ExtraProductDataItem(key,value)));
            domainObject.setExtraProductData(extraDataItems);
        }
        productDeviceGateway.saveProductDevice(domainObject);
        return Response.buildSuccess();
    }
}
