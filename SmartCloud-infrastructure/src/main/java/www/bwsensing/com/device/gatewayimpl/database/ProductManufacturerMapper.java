package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductManufacturerDO;

import java.util.List;

/**
 * 产品厂商
 * @author macos-zyj
 */
public interface ProductManufacturerMapper {
    /**
     * 保存
     * @param manufacturerDO
     */
    void  save(ProductManufacturerDO manufacturerDO);


    /**
     * 修改
     * @param manufacturerDO
     */
    void  update(ProductManufacturerDO manufacturerDO);


    /**
     * 根据Id进行查询
     * @param id
     * @return
     */
    ProductManufacturerDO selectManufacturerById(Integer id);

    /**
     * 条件组合查询
     * @param querySort
     * @return
     */
    List<ProductManufacturerDO> selectManufacturesBySort(ProductManufacturerDO querySort);
}
