package www.bwsensing.com.common.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class ClientStreamExecute implements Serializable {

    @Resource
    private DomainEventPublisher eventPublisher;

    public void execute()  {
        try {
            while (true) {
                if (ClientStreamContainer.getSize() > 0){
                    eventPublisher.publish(ClientStreamContainer.poll());
                } else {
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException ignore){
            log.info("client stream execute interrupted");
        }
    }
}
