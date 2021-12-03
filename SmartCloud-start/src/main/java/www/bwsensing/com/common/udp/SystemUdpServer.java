package www.bwsensing.com.common.udp;

import java.util.Date;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import org.springframework.integration.ip.dsl.Udp;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import www.bwsensing.com.common.utills.AddressUtils;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domainevent.FacilityDataReceiveEvent;


/**
 * UDP消息接收服务
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/20 14:16
 */
@Slf4j
@Configuration
public class SystemUdpServer {

    private static final Logger logger = LoggerFactory.getLogger(SystemUdpServer.class);

    @Value("${system.udp.port}")
    private Integer port;

    @Resource
    private DomainEventPublisher domainEventPublisher;

    /**
     * UDP消息接收服务写法二
     * https://docs.spring.io/spring-integration/reference/html/ip.html#inbound-udp-adapters-java-dsl-configuration
     *
     * @param
     * @return org.springframework.integration.dsl.IntegrationFlow
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/20 16:08
     */
    @Bean
    public IntegrationFlow integrationFlow() {
        logger.info("UDP服务启动成功，端口号为: {}", port);
        return IntegrationFlows.from(Udp.inboundAdapter(port)).channel("udpChannel").get();
    }

    /**
     * 转换器
     *
     * @param payload
	 * @param headers
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/20 15:30
     */
    @Transformer(inputChannel = "udpChannel", outputChannel = "udpFilter")
    public String transformer(@Payload byte[] payload, @Headers Map<String, Object> headers) {
        return new String(payload);
    }

    /**
     * 过滤器
     *
     * @param message
	 * @param headers
     * @return boolean
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/20 15:30
     */
    @Filter(inputChannel = "udpFilter", outputChannel = "udpRouter")
    public boolean filter(String message, @Headers Map<String, Object> headers) {
        return true;
    }

    /**
     * 路由分发处理器
     *
     * @param message
	 * @param headers
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/20 15:35
     */
    @Router(inputChannel = "udpRouter")
    public String router(String message, @Headers Map<String, Object> headers) {
        return  "udpHandle";
    }

    /**
     * 处理器
     *
     * @param message
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/20 15:12
     */
    @ServiceActivator(inputChannel = "udpHandle")
    public void udpMessageHandle(String message, @Headers Map<String, Object> headers) throws Exception {
        String ipAddress = headers.get("ip_address").toString();
        if (StringUtils.isNotEmpty(message)){
            Date receiveTime = new Date();
            FacilityDataReceiveEvent receiveEvent = new FacilityDataReceiveEvent();
            receiveEvent.setReceiveData(message);
            setReceiveEvent(receiveEvent,receiveTime);
            domainEventPublisher.transactionPublish(receiveEvent);
            log.info("facility data pushed,Client address:{}",ipAddress);
        }
        logger.info("UDP message:" + message);
    }

    private void setReceiveEvent(FacilityDataReceiveEvent receive,Date receiveTime){
        receive.setChannelId("UDP");
        receive.setReceiveTime(receiveTime);
    }
}
