package www.bwsensing.com.domain.device.gateway;

import java.util.List;
import www.bwsensing.com.domain.device.model.ProductModel;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import www.bwsensing.com.domain.device.model.ExtraProductDataItem;
import www.bwsensing.com.domain.device.model.data.model.DataComputationModel;
import www.bwsensing.com.domain.device.model.data.model.DataModelItem;
import www.bwsensing.com.domain.device.model.data.model.ProductDataModel;

/**
 * 产品型蛤
 * @author macos-zyj
 */
public interface ProductModelGateway {
    /**
     * 保存
     * @param saveModel
     */
    void saveModel(ProductModel saveModel);

    /**
     * 修改
     * @param updateModel
     */
    void updateModel(ProductModel updateModel);

    /**
     * 删除
     * @param modelId
     */
    void deleteModel(Integer modelId);

    /**
     * 添加产品数据模型
     * @param dataModel
     */
    void addProductDataModel(ProductDataModel dataModel);

    /**
     * 修改产品数据模型
     * @param dataModel
     */
    void updateProductDataModel(ProductDataModel dataModel);

    /**
     * 删除产品数据模型
     * @param id
     */
    void deleteProductDataModel(Integer id);


    /**
     * 添加产品解析数据项
     * @param dataModelItem
     */
    void addProductDataModelItem(DataModelItem dataModelItem);

    /**
     * 修改产品解析数据项
     * @param dataModelItem
     */
    void updateProductDataModelItem(DataModelItem dataModelItem);

    /**
     * 删除产品解析数据项
     * @param id
     */
    void deleteProductDataModelItem(Integer id);

    /**
     * 添加产品数据监测项
     * @param productDataItem
     */
    void addProductDataItem(ProductDataItem productDataItem);

    /**
     * 修改产品数据监测项
     * @param productDataItem
     */
    void updateProductDataItem(ProductDataItem productDataItem);

    /**
     * 根据Id进行产品数据监测项删除
     * @param id
     */
    void deleteProductDataItemById(Integer id);

    /**
     * 新增额外产品信息项
     * @param extraProductDataItem
     */
    void addExtraProductDataItem(ExtraProductDataItem extraProductDataItem);

    /**
     * 修改额外产品信息项
     * @param extraProductDataItem
     */
    void updateExtraProductDataItem(ExtraProductDataItem extraProductDataItem);

    /**
     * 编号
     * @param id
     */
    void deleteExtraProductDataItemById(Integer id);

    /**
     * 根据绑定的网络模型编号获取对应的数据模型集合
     * @param webModelId
     * @return
     */
    List<ProductDataModel> getDataModelByWebId(Integer webModelId);


    /**
     * 添加计算模型
     * @param dataComputationModel
     */
    void addProductDataComputationModel(DataComputationModel dataComputationModel);

    /**
     * 修改计算模型
     * @param dataComputationModel
     */
    void updateProductDataComputationModel(DataComputationModel dataComputationModel);

    /**
     * 根据主键删除计算模型
     * @param id
     */
    void deleteProductDataComputationModelById(Integer id);

    /**
     * 根据编号获取对应的计算模型
     * @param id
     * @return
     */
    DataComputationModel getDataComputationModelById(Integer id);
}
