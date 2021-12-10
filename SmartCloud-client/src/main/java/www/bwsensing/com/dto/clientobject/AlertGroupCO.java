package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;


@Data
@SuppressWarnings("all")
public class AlertGroupCO extends DTO {
    /** 主键 */
    private Integer id;

    /** 预警分组名称 */
    private String groupName;

    /** 操作组 */
    private Integer operateGroupId;

    /** 当前传感器 */
    private Integer currentSensorId;

    /** 模板编号 */
    private Integer templateId;

    /** 创建者 */
    private String creator;

    /**创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
