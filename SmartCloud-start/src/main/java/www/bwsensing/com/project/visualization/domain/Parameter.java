package www.bwsensing.com.project.visualization.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
/**
 * 请求参数
 * @author macos-zyj
 */
@Data
public class Parameter {
    /**
     * 主键
     */
    @JsonIgnore
    private Integer id;
    /**
     * 参数编码
     */
    private String paramNo;


    /**
     * 小数点长度
     */
    private Integer decimalSize;

    /**
     * 单位
     */
    private String unit;

    /**参数详情*/
    private String paramId;
    /**
     * 参数名称
     */
    private String paramName;
    /**
     * 模型编号
     */
    @JsonIgnore
    private String modelNo;

}
