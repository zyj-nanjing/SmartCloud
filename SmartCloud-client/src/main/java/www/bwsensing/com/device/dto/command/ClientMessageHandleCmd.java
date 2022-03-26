package www.bwsensing.com.device.dto.command;

import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.dto.Command;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class ClientMessageHandleCmd extends Command {
    /**
     * 接收的JSON数据
     */
    private String jsonData;
    /**用例场景*/
    private String scenario;
    private String bizId;
    private BizScenario bizScenario;
}
