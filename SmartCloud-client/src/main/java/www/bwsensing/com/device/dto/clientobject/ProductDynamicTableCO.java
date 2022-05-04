package www.bwsensing.com.device.dto.clientobject;

import java.util.List;
import com.alibaba.cola.dto.DTO;
import www.bwsensing.com.common.clientobject.DataItemsCO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductDynamicTableCO extends DTO {

    /**
     * 传感器对应的数据项
     */
    private List<DataItemsCO> dataItems;

    /**
     * 列数据
     */
    private List<ProductDynamicColumnCO> columns;

    public ProductDynamicTableCO() {

    }

    public List<DataItemsCO> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<DataItemsCO> dataItems) {
        this.dataItems = dataItems;
    }

    public List<ProductDynamicColumnCO> getColumns() {
        return columns;
    }

    public void setColumns(List<ProductDynamicColumnCO> columns) {
        this.columns = columns;
    }
}
