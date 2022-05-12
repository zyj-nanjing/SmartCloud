package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class DataComputationItemDO {

    private Integer computationId;
    /**
     * 占位前缀
     */
    private String prefix;

    private Integer itemKind;
    /**
     * 选中的编号
     */
    private Integer currentId;
}
