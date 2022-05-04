package www.bwsensing.com.device.command;

import com.alibaba.cola.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.dto.command.ProductUpdateCmd;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.domain.device.model.ExtraProductDataItem;
import www.bwsensing.com.domain.device.model.ProductDevice;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class ProductUpdateCmdExo {
    @Resource
    private ProductDeviceGateway productDeviceGateway;

    public Response execute(ProductUpdateCmd updateCmd){
        ProductDevice domainObject = new ProductDevice();
        BeanUtils.copyProperties(updateCmd, domainObject);
        if (null != updateCmd.getExtraData()){
            List<ExtraProductDataItem> extraDataItems = new ArrayList<>();
            updateCmd.getExtraData().forEach((key,value) -> extraDataItems.add(new ExtraProductDataItem(key,value)));
            domainObject.setExtraProductData(extraDataItems);
        }
        productDeviceGateway.updateProductDevice(domainObject);
        return Response.buildSuccess();
    }
}
