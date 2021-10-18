package www.bwsensing.com.domain.monitor;

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
    /**ASCII 解析标位*/
    private Integer asciiIndex;
    /**HEX 解析标位*/
    private Integer hexIndex;
    /**ASCII 解析前缀*/
    private String asciiPrefix;
    /**HEX减 偏移量*/
    private Integer  reduceNumber;
    /**HEX除 偏移量*/
    private Integer  divideNumber;

}
