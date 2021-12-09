package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class AlarmTemplateUpdateCmd extends Command {
    /**主键*/
    @NotNull(message = "主键不能为空")
    private Integer id;
    private String templateName;
    /**模型编号 告警模板对应的设备型号*/
    private Integer modelNo;
    /**告警role前缀名称 唯一会校验唯一性*/
    private String namePrefix;
    /**监测参数*/
    @Valid
    @NotNull(message = "监测参数不能为空")
    @Size(min = 1, message = "监测参数至少要有一个自定义属性")
    private List<AlertParamSaveCmd> alertParams;
    /**
     * 告警信息模板   支持占位符 ${item} 检测项名称  ${paramName} 当前数值   ${sensor} 传感器名称  ${alertName} 对应监测相位告警信息 ${paramName}
     * 类似格式 传感器:${sensor} 发生 ${alertName} 问题 当前 ${paramName} 监测查询值为:${paramName}
     **/
    private String summaryModel;
}
