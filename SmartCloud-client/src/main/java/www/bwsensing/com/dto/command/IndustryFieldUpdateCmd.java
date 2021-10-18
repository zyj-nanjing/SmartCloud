package www.bwsensing.com.dto.command;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class IndustryFieldUpdateCmd {
    /**主键*/
    private Integer id;
    /**行业领域名称*/
    private String name;
}
