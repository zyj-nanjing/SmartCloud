package www.bwsensing.com.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class AlertTemplateQuery extends PageQuery {
    /***
     * 模型编号
     */
    private Integer modelNo;
    /**
     * 模型名称
     */
    private String templateName;
    /**
     * 监测前缀
     */
    private String namePrefix;
}
