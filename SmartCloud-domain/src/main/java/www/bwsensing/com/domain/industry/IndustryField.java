package www.bwsensing.com.domain.industry;

import lombok.Data;

import java.util.Date;
/**
 * 行业领域
 * @author macos-zyj
 */
@Data
public class IndustryField {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**行业编码*/
    private String code;
    /**创建时间*/
    private Date createTime;
}
