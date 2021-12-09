package www.bwsensing.com.gatewayimpl;

import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.convertor.ManufacturerConvertor;
import www.bwsensing.com.domain.device.manufacturer.ProductManufacturer;
import www.bwsensing.com.domain.gateway.ManufacturerGateway;
import www.bwsensing.com.gatewayimpl.database.ProductManufacturerMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.ProductManufacturerDO;

import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class ManufacturerGatewayImpl implements ManufacturerGateway {
    @Resource
    private ProductManufacturerMapper manufacturerMapper;

    @Override
    public void saveManufacturer(ProductManufacturer manufacturer) {
        manufacturer.create();
        ProductManufacturerDO saveData = ManufacturerConvertor.toDataObject(manufacturer);
        validateManufacturer(saveData);
        manufacturerMapper.save(saveData);
    }

    @Override
    public void updateManufacturer(ProductManufacturer manufacturer) {
        ProductManufacturerDO updateData = ManufacturerConvertor.toDataObject(manufacturer);
        validateManufacturer(updateData);
        manufacturerMapper.update(updateData);
    }

    private void validateManufacturer(ProductManufacturerDO validator){
        if(null != validator.getId()){
            ProductManufacturerDO queryResult = manufacturerMapper.selectManufacturerById(validator.getId());
            if(StringUtils.isEmpty(validator.getName())|| queryResult.getName().equals(validator.getName())){
                return;
            }
        }
        if(StringUtils.isNotEmpty(validator.getName())){
            ProductManufacturerDO validQuery = new ProductManufacturerDO();
            validQuery.setName(validator.getName());
            if(manufacturerMapper.selectManufacturesBySort(validQuery).size()>0){
                throw new BizException("MANUFACTURE_HAVE_EXIST","该供应商已存在!");
            }
        }
    }
}
