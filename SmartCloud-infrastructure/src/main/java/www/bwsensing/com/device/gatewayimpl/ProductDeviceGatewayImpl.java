package www.bwsensing.com.device.gatewayimpl;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.ObjectConvertUtils;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.device.convertor.ProductDeviceConvertor;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.device.gatewayimpl.database.ExtraProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ExtraProductDataItemDO;
/**
 * @author macos-zyj
 */
@Component
public class ProductDeviceGatewayImpl implements ProductDeviceGateway {
    @Resource
    private ProductDeviceMapper productDeviceMapper;

    @Resource
    private ExtraProductDataItemMapper extraProductDataItemMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveProductDevice(ProductDevice device) {
        if(null != productDeviceMapper.getProductDetailByUniqueCode(device.getUniqueCode())){
            throw new BizException("CURRENT_DEVICE_UNIQUE_CODE_EXIST","当前设备唯一编码已存在");
        }
        if (device.getNeedSituation()){
            Assert.notNull(device.getLongitude(),"经度不能为空!");
            Assert.notNull(device.getLatitude(),"纬度不能为空!");
        }
        ProductDeviceDO dataObject = ProductDeviceConvertor.toDataObject(device);
        productDeviceMapper.saveProductDevice(dataObject);
        if(null  != device.getExtraProductData()){
            device.getExtraProductData().forEach(extraData -> saveDeviceExtraData(device.getId(),extraData.getId(),extraData.getExtraData(),device.getModelId()));
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateProductDevice(ProductDevice device) {
        ProductDeviceDO dataObject = productDeviceMapper.getProductDetailById(device.getId());
        if(null != dataObject){
            ProductDevice dataBaseObject = ProductDeviceConvertor.toDomain(dataObject);
            ObjectConvertUtils.copyProperties(device,dataBaseObject);
            productDeviceMapper.updateProductDevice(ProductDeviceConvertor.toDataObject(dataBaseObject));
        } else {
            throw new BizException("NO_PRODUCT_DEVICE_FOUND","该设备不存在!");
        }
        if(null  != device.getExtraProductData()){
            extraProductDataItemMapper.deleteExtraProductItemDataByDeviceId(device.getId());
            device.getExtraProductData().forEach(extraData -> saveDeviceExtraData(device.getId(),extraData.getId(),extraData.getExtraData(),dataObject.getModelId()));
        }
    }

    public void saveDeviceExtraData(Integer deviceId, Integer extraId,String extraData,Integer modelId){
        ExtraProductDataItemDO extraItem = extraProductDataItemMapper.getExtraProductDataItemById(extraId);
        if(null  != extraItem){
            if(extraItem.getModelId().equals(modelId)){
                productDeviceMapper.saveDeviceExtraData(deviceId, extraId,extraData);
            }
        }
    }

    @Override
    public void updateProductDevices(List<ProductDevice> devices) {
        devices.forEach(this::updateProductDevice);
    }

    @Override
    public void deleteProductDeviceById(Integer id) {
        productDeviceMapper.deleteById(id);
    }

    @Override
    public List<ProductDevice> getSensorsByMonitorStructure(Integer structureId) {
        List<ProductDeviceDO> sensorDataCollection = productDeviceMapper.getSensorsByMonitorStructure(structureId);
        return ProductDeviceConvertor.toDomainCollection(sensorDataCollection);
    }

    @Override
    public ProductDevice getDeviceDetailById(Integer deviceId) {
        ProductDeviceDO productDeviceDo = productDeviceMapper.getProductDetailById(deviceId);
        if(null != productDeviceDo){
            return ProductDeviceConvertor.toDomain(productDeviceDo);
        }
        throw new BizException("DEVICE_NOT_FOUND","设备不存在");
    }

    @Override
    public List<ProductDataItem> getProductDataItemByUniqueId(Integer uniqueId) {
        ProductDeviceDO productDeviceDo = productDeviceMapper.getProductDetailById(uniqueId);
        if(null != productDeviceDo){

        }
        return null;
    }
}
