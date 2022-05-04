package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.*;
import www.bwsensing.com.device.dto.command.ProductSaveCmd;
import www.bwsensing.com.device.dto.command.ProjectBindWithProductCmd;
import www.bwsensing.com.device.dto.command.query.FacilityReceivePageQuery;
import www.bwsensing.com.device.dto.command.query.ProductSortQuery;
import www.bwsensing.com.device.dto.command.ProductUpdateCmd;

/**
 * @author macos-zyj
 */
public interface ProductDeviceService {
    /**
     * 传感器分页查询
     * @param sortQuery
     * @return
     */
    PageResponse<ProductDeviceCO> queryProductByPageSort(ProductSortQuery sortQuery);


    /**
     * 传感器查询
     * @param sortQuery
     * @return
     */
    MultiResponse<ProductDeviceCO> queryProductsBySort(ProductSortQuery sortQuery);


    /**
     * 查询数据接收日志
     * @param receivePageQuery
     * @return
     */
    PageResponse<FacilityReceiveCO> queryFacilitySendsByUniqueCode(FacilityReceivePageQuery receivePageQuery);


    /**
     * 获取企业下当前传感器数据
     * @return
     */
    SingleResponse<String> getProductCurrentData();

    /**
     * ID 查询
     * @param id
     * @return
     */
    SingleResponse<ProductDeviceCO> queryProductDetailById(Integer id);

    /***
     * 根据项目编号获取传感器集合
     * @param projectId
     * @return
     */
    MultiResponse<ProductDeviceCO> queryProductsByProjectId(Integer projectId);

    /**
     * 查询首页传感器信息
     * @return
     */
    MultiResponse<ProductMapDetailCO> getProductsWithIndexMap();

    /**
     * 保存传感器
     * @param saveCmd
     * @return
     */
    Response saveProduct(ProductSaveCmd saveCmd);

    /**
     * 修改传感器
     * @param updateCmd
     * @return
     */
    Response updateProduct(ProductUpdateCmd updateCmd);

    /**
     * 关联传感器和项目
     * @param projectBindCmd
     * @return
     */
    Response productBindWithProject(ProjectBindWithProductCmd projectBindCmd);
    /**
     * 根据传感器编号删除
     * @param id
     * @return
     */
    Response deleteProduct(Integer id);

    /**
     * 根据测点获取传感器待绑定以及绑定的传感器
     * @param positionId
     * @return
     */
    MultiResponse<ProductBindStatusCO> getProductBindStatusWithPositionId(Integer positionId);

    /**
     * 获取设备监测编码集合
     * @param uniqueCode
     * @return
     */
    MultiResponse<ProductDataItemCO> getProductDataItemsByUniqueCode(String uniqueCode);
}
