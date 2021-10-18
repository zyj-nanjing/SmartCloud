package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.device.manufacturer.ProductManufacturer;
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
