package www.bwsensing.com.domain.monitor.model;

import lombok.Data;

/**
 * 监测项 单位 数据类型
 * 是否考虑监测项与产品数据解析挂钩
 * @author macos-zyj
 */
@Data
public class MonitorItem {
    private Integer id;
    /**名称**/
    private String itemName;
    /**对应数据名称**/
    private String dataId;
    /**单位**/
    private String unit;
    /**
     * 数据项类型
     */
    private MonitorItemKind itemKind;


    public MonitorItem() {
    }

    public MonitorItem(String dataId) {
        this.dataId = dataId;
    }
}
