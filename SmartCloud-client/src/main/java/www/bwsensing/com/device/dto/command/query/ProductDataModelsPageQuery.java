package www.bwsensing.com.device.dto.command.query;


import lombok.Data;
import com.alibaba.cola.dto.PageQuery;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProductDataModelsPageQuery extends PageQuery {

    /**
     * 产品型号编号
     */
    @NotNull(message = "产品型号编号不能为空!")
    private Integer modelId;

    /**
     * 名称
     */
    private String name;

    /**
     * 数据形式
     */
    private Integer dataFormId;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 进制
     */
    private Integer carrySystem;

    /**
     * 分隔方式(枚举)
     */
    private Integer splitMethodId;

    /**
     * 分隔符
     */
    private String separator;

    /**
     * 排序
     */
    private Integer weight;

    /**
     * 基础数据单元长度
     */
    private Integer baseDataSize;

    /**
     * 期待数据长度
     */
    private Integer expectDataSize;
}
