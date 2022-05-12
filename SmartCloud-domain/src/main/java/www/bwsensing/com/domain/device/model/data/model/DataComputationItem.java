package www.bwsensing.com.domain.device.model.data.model;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class DataComputationItem {
    /**
     * 占位前缀
     */
    private String prefix;
    /**
     * 对应的数据相位的类型
     */
    private Integer itemKind;
    /**
     * 选中的编号
     */
    private Integer currentId;
}
