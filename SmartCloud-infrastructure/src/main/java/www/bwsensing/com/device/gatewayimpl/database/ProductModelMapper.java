package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductModelDO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ProductModelMapper {
    /**
     * 获取模板名称的数量
     * @param name
     * @return
     */
    Integer countModelByName(String name);

    /**
     * 查询产品型号
     *
     * @param id 产品型号主键
     * @return 产品型号
     */
    ProductModelDO getProductModelById(Integer id);

    /**
     * 查询产品型号列表
     *
     * @param productModel 产品型号
     * @return 产品型号集合
     */
    List<ProductModelDO> queryProductModelBySort(ProductModelDO productModel);

    /**
     * 新增产品型号
     *
     * @param productModel 产品型号
     * @return 结果
     */
    int saveProductModel(ProductModelDO productModel);

    /**
     * 修改产品型号
     *
     * @param productModel 产品型号
     * @return 结果
     */
    int updateProductModel(ProductModelDO productModel);

    /**
     * 删除产品型号
     *
     * @param id 产品型号主键
     * @return 结果
     */
    int deleteProductModelById(Integer id);

    /**
     * 批量删除产品型号
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteProductModelByIds(String[] ids);

    /**
     * 保存产品与行业领域的关联
     * @param modelId
     * @param industryId
     */
    void saveProductModelWithIndustry(@Param("modelId")Integer modelId, @Param("industryId")Integer industryId);


    /**
     * 根据模型编号删除产品与行业领域的关联
     * @param modelId
     */
    void deleteProductModelWithIndustryByModelId(Integer modelId);
}
