package www.bwsensing.com.device.dto.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alibaba.cola.dto.Command;
import lombok.Data;


/**
 * @author macos-zyj
 */
@Data
public class ProductDataModelUpdateCmd extends Command {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空!")
    private Integer id;

    /**
     * 名称
     */
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
    private Integer carrySystem;

    /**
     * 分隔方式(枚举)
     */
    @NotNull(message = "分隔方式不能为空!")
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
    private Integer expectDataSize;


    /**
     * 备注
     */
    private String remark;
}
