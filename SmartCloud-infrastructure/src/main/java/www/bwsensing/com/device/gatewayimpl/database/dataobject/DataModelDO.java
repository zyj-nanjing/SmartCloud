package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class DataModelDO {
    /**
     * 主键
     */
    private Integer id;

    private Integer modelId;

    /**
     * 数据形式
     */
    private Integer dataForm;

    /**
     * 名称
     */
    private String name;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 进制
     */
    private Integer carrySystem;

    private Integer expectDataSize;
    /**
     * 分隔方式(枚举)
     */
    private String splitMethod;

    /**
     * 排序
     */
    private Integer weight;

    /**
     * 分隔符
     */
    private String separator;

    /**
     * 基础数据单元长度
     */
    private Integer baseDataSize;

    /**
     * 数据项
     */
    private List<DataModelItemDO> dataItems;

    /**
     * 对应数据格式的正则
     */
    private String dataFormat;

    /**
     * 备注
     */
    private String remark;
}
