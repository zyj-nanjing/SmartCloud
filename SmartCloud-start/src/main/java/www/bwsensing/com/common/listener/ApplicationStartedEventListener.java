package www.bwsensing.com.common.listener;

import www.bwsensing.com.common.client.ClientCheckService;
import www.bwsensing.com.common.scheduler.database.dataobject.ServiceDeploy;
import www.bwsensing.com.common.scheduler.database.ServiceDeployMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import www.bwsensing.com.common.utills.NetworkInterfaceUtil;
import org.springframework.context.ApplicationListener;
import www.bwsensing.com.common.config.ServiceConfig;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * @author macos-zyj
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private ClientCheckService clientCheckService;
    @Resource
    private ServiceDeployMapper serviceDeployMapper;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
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
        clientCheckService.mqttClientCheck();
    }


}
