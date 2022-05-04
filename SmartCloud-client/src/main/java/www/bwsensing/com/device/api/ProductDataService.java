package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.ProductDynamicTableCO;
import www.bwsensing.com.device.dto.command.query.ProductDataTableQuery;

/**
 * 设备数据服务
 * @author macos-zyj
 */
public interface ProductDataService {

    /**
     * 动态表格查询
     * @param tableQuery
     * @return
     */
    SingleResponse<ProductDynamicTableCO> productDynamicTableQuery(ProductDataTableQuery tableQuery);
}
