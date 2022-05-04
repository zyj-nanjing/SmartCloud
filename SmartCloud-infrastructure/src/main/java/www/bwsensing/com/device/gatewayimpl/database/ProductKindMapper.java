package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductKindDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ProductKindMapper {
    /**
     * 查询产品类型
     * @param id 产品类型
     * @return 产品类型
     */
    ProductKindDO getProductKindById(Long id);

    /**
     * 查询产品类型
     * @param productKind 产品类型
     * @return 产品类型
     */
    List<ProductKindDO> queryProductKindBySort(ProductKindDO productKind);

    /**
     * 新增产品类型
     * @param productKind 产品类型
     * @return 结果
     */
    int saveProductKind(ProductKindDO productKind);

    /**
     * 修改产品类型
     * @param productKind 产品类型
     * @return 结果
     */
    int updateProductKind(ProductKindDO productKind);

    /**
     * 删除产品类型
     * @param id 产品类型
     * @return 结果
     */
    int deleteProductKindById(Long id);

    /**
     * 批量删除产品类型
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteProductKindByIds(String[] ids);
}
