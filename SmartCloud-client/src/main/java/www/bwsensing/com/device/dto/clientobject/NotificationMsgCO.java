package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
@Data
public class NotificationMsgCO  extends DTO {
    private Integer id;
    private String message;
    public  Boolean status;
}
