package www.bwsensing.com.device.gatewayimpl;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.exception.TaskException;
import www.bwsensing.com.common.quartz.database.dataobject.SysJob;
import www.bwsensing.com.common.quartz.service.ISysJobService;
import www.bwsensing.com.common.utills.ObjectConvertUtils;
import www.bwsensing.com.device.convertor.DeviceComputationConvertor;
import www.bwsensing.com.device.gatewayimpl.database.DeviceComputationMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;
import www.bwsensing.com.domain.device.model.ComputationHandleKind;
import www.bwsensing.com.domain.device.model.DeviceComputation;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.device.convertor.ProductDeviceConvertor;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.device.gatewayimpl.database.ExtraProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ExtraProductDataItemDO;
import www.bwsensing.com.domain.device.model.data.model.ComputationKind;

/**
 * @author macos-zyj
 */
@Component
public class ProductDeviceGatewayImpl implements ProductDeviceGateway {
    private static final String BUSINESS_LINE = "DEVICE_COMPUTATION";
    @Resource
    private ProductDeviceMapper productDeviceMapper;

    @Resource
    private ExtraProductDataItemMapper extraProductDataItemMapper;

    @Resource
    private DeviceComputationMapper deviceComputationMapper;

    @Resource
    private ISysJobService sysJobService;


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

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void addDeviceComputation(DeviceComputation deviceComputation) {
        deviceComputation.saveCheck();
        DeviceComputationDO dataObject = DeviceComputationConvertor.toDataObject(deviceComputation);
        deviceComputationMapper.insertDeviceComputation(dataObject);
        if (ComputationKind.SCHEDULED_CALCULATION.equals(deviceComputation.getComputationKind())){
            createSysJob(deviceComputation, dataObject);
            Assert.notNull(deviceComputation.getHandleKind(),"处理类型不能为空!");
            if (ComputationHandleKind.INTERVAL_DATA.equals(deviceComputation.getHandleKind())){
                Assert.notNull(deviceComputation.getFunctionCode(),"处理函数不能为空!");
            }
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateDeviceComputation(DeviceComputation deviceComputation) {
        DeviceComputationDO dataObject = deviceComputationMapper.getDeviceComputationById(deviceComputation.getId());
        DeviceComputation dataBaseObject;
        if(null != dataObject){
            dataBaseObject = DeviceComputationConvertor.toDomain(dataObject);
            ObjectConvertUtils.copyProperties(deviceComputation,dataBaseObject);
            deviceComputationMapper.updateDeviceComputation(DeviceComputationConvertor.toDataObject(dataBaseObject));
        } else {
            throw new BizException("NO_DEVICE_COMPUTATION","该设备计算模型关联不存在!");
        }
        if (ComputationKind.SCHEDULED_CALCULATION.equals(dataBaseObject.getComputationKind())){
            if (null == dataBaseObject.getJobId()){
                createSysJob(deviceComputation, dataObject);
            } else {
                SysJob currentJob = sysJobService.selectJobById(Long.valueOf(dataObject.getJobId()));
                if(null != currentJob){
                    currentJob.setCronExpression(dataBaseObject.getCronExpression());
                    currentJob.setConcurrent(dataBaseObject.getConcurrent()?"0":"1");
                    currentJob.setStatus(dataBaseObject.getStatus()?"0":"1");
                    currentJob.setBusinessLine(BUSINESS_LINE);
                    try {
                        sysJobService.updateJob(currentJob);
                    } catch (SchedulerException | TaskException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void deleteDeviceComputation(Integer id) {
        DeviceComputationDO dataObject = deviceComputationMapper.getDeviceComputationById(id);
        if(null != dataObject){
            deviceComputationMapper.deleteDeviceComputationById(id);
            if (null != dataObject.getJobId()){
                SysJob currentJob = sysJobService.selectJobById(Long.valueOf(dataObject.getJobId()));
                if(null != currentJob){
                    try {
                        sysJobService.deleteJob(currentJob);
                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void createSysJob(DeviceComputation deviceComputation, DeviceComputationDO dataObject) {
        SysJob sysJob = new SysJob();
        sysJob.setJobName(deviceComputation.initScheduledName());
        deviceComputation.setId(dataObject.getId());
        sysJob.setJobGroup("设备数据计算");
        sysJob.setBusinessLine(BUSINESS_LINE);
        sysJob.setStatus("0");
        sysJob.setInvokeTarget(deviceComputation.initInvokeTarget());
        sysJob.setCronExpression(deviceComputation.getCronExpression());
        sysJob.setConcurrent(deviceComputation.getConcurrent()?"0":"1");
        try {
            sysJobService.insertJob(sysJob);
            sysJobService.resumeJob(sysJob);
            dataObject.setJobId(sysJob.getJobId().intValue());
            deviceComputationMapper.updateDeviceComputation(dataObject);
        } catch (Exception  ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProductDataItem> getProductDataItemByUniqueId(Integer uniqueId) {
        ProductDeviceDO productDeviceDo = productDeviceMapper.getProductDetailById(uniqueId);
        if(null != productDeviceDo){

        }
        return null;
    }
}
