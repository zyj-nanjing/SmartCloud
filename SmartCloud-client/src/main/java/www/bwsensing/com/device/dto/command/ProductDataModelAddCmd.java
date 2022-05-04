package www.bwsensing.com.device.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProductDataModelAddCmd extends Command {
    /**
     * 产品型号编号
     */
    @NotNull(message = "产品型号编号不能为空!")
    private Integer modelId;

    /**
     * 名称
     */
    @NotBlank(message = "产品数据模型名称不能为空!")
    private String name;

    /**
     * 数据形式
     */
    @NotNull(message = "数据形式不能为空!")
    private Integer dataForm;

    /**
     * 消息类型
     */
    @NotBlank(message = "消息类型不能为空!")
    private String messageType;

    /**
     * 进制
     */
    @NotNull(message = "数据进制不能为空!")
    private Integer carrySystem;

    /**
     * 分隔方式(枚举)
     */
    @NotNull(message = "数据分隔方式不能为空!")
    private Integer splitMethod;

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
    @NotNull(message = "期待数据长度不能为空!")
    private Integer expectDataSize;


    /**
     * 对应数据格式的正则
     */
    private String dataFormat;

    /**
     * 备注
     */
    private String remark;
}
