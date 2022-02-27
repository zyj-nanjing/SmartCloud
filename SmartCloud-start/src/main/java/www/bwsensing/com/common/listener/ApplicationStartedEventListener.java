package www.bwsensing.com.common.listener;

import org.springframework.context.annotation.Profile;
import www.bwsensing.com.common.client.ClientScheduler;
import www.bwsensing.com.common.scheduler.database.dataobject.ServiceDeploy;
import www.bwsensing.com.common.scheduler.database.ServiceDeployMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import www.bwsensing.com.common.utills.NetworkInterfaceUtil;
import www.bwsensing.com.common.thread.NamedThreadFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import www.bwsensing.com.common.config.ServiceConfig;
import www.bwsensing.com.common.netty.NettyTcpServer;
import www.bwsensing.com.common.netty.NettyUdpServer;
import org.springframework.stereotype.Component;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import java.util.List;
/**
 * @author macos-zyj
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * 默认线程池
     *     如果处理器无定制线程池，则使用此默认
     */
    private static final ExecutorService DEFAULT_EXECUTOR = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1000),
            new NamedThreadFactory("ASYNC-NETTY-POOL"));


    @Resource
    private ServiceDeployMapper serviceDeployMapper;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        NettyTcpServer nettyTcpServer = applicationContext.getBean(NettyTcpServer.class);
        NettyUdpServer nettyUdpServer = applicationContext.getBean(NettyUdpServer.class);
        DEFAULT_EXECUTOR.submit(nettyTcpServer::run);
        DEFAULT_EXECUTOR.submit(nettyUdpServer::run);
        registerService();
    }

    private void registerService() {
        String ipAddress = NetworkInterfaceUtil.getHostIp();
        String hostname = NetworkInterfaceUtil.getHostName();
        List<ServiceDeploy> deploys = serviceDeployMapper.selectServiceDeployList(new ServiceDeploy(hostname,ipAddress));
        if (null == deploys||deploys.size() ==0){
            ServiceDeploy deploy = new ServiceDeploy();
            deploy.setIpv4Inner(ipAddress);
            deploy.setLocation(ServiceConfig.LOCATION);
            deploy.setHostName(hostname);
            deploy.setConfigure(ServiceConfig.CONFIGURATION);
            deploy.setWeight(ServiceConfig.WEIGHT);
            deploy.setRemark(ServiceConfig.REMARK);
            serviceDeployMapper.insertServiceDeploy(deploy);
        }
    }


}
