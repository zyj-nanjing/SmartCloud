package www.bwsensing.com.device.extensionpoint;

import com.alibaba.cola.extension.ExtensionPointI;
import www.bwsensing.com.device.dto.command.ClientMessageHandleCmd;

/**
 * @author macos-zyj
 */
public interface ClientMessageHandleExtPt extends ExtensionPointI {
    /**
     * 获取并处理
     * @param handleCmd
     */
    void getAndHandler(ClientMessageHandleCmd handleCmd);
}
