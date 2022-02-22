package www.bwsensing.com.domain.monitor.gateway;

import www.bwsensing.com.domain.device.model.manufacturer.ProductManufacturer;
/**
 * @author macos-zyj
 */
public interface ManufacturerGateway {
    /**
     * 保存设备厂商
     * @param manufacturer
     */
    void saveManufacturer(ProductManufacturer manufacturer);

    /**
     * 修改设备厂商
     * @param manufacturer
     */
    void updateManufacturer(ProductManufacturer manufacturer);
}
