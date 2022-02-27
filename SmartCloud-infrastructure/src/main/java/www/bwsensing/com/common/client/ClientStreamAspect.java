package www.bwsensing.com.common.client;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.common.annotation.ClientStreamRegister;
import www.bwsensing.com.common.client.database.DataStreamSourceMapper;
import www.bwsensing.com.common.client.database.dataobject.DataStreamSource;
import www.bwsensing.com.common.config.ServiceConfig;
import www.bwsensing.com.common.utills.ObjectConvertUtils;
import www.bwsensing.com.event.ClientStreamEventI;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Profile("test")
@Component
@Aspect
@Slf4j
public class ClientStreamAspect  {

    @Resource
    private ServiceConfig serviceConfig;

    @Resource
    private DataStreamSourceMapper dataStreamSourceMapper;


    @Pointcut("@annotation(www.bwsensing.com.common.annotation.ClientStreamRegister)")
    private void pointcut() {

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Around(value = "pointcut() && @annotation(registry)", argNames = "pjp,registry")
    public Object before(ProceedingJoinPoint pjp, ClientStreamRegister registry) throws Throwable {
        Object obj;
        ClientStreamEventI[] clients = ObjectConvertUtils.convertArray(ClientStreamEventI.class,pjp.getArgs());
        obj = pjp.proceed();
        ClientStreamEventI clientEvent = clients[0];
        log.info("client stream aop pointcut data:{}",clientEvent);
        DataStreamSource dataSource =
                dataStreamSourceMapper.queryDataStreamSource(serviceConfig.getNamespace(),clientEvent.getJobName());
        if ( null != dataSource ){
            switch(PushKindEnum.getPushKind(clientEvent.getMessageKind())){
                case MQTT:
                {
                    if (dataStreamSourceMapper.checkMqttTopicExist(clientEvent.getTopic(),dataSource.getId())==0){
                        dataStreamSourceMapper.insertMqttTopic(clientEvent.getTopic(),dataSource.getId());
                    }
                    break;
                }
                case ROCKETMQ:
                default:{
                    break;
                }
            }
        } else {
            dataSource = new DataStreamSource();
            dataSource.setJobName(clientEvent.getJobName());
            dataSource.setNamespace(serviceConfig.getNamespace());
            dataStreamSourceMapper.saveDataStream(dataSource);
            switch(PushKindEnum.getPushKind(clientEvent.getMessageKind())){
                case MQTT:
                {
                    dataStreamSourceMapper.insertMqttTopic(clientEvent.getTopic(),dataSource.getId());
                    break;
                }
                case ROCKETMQ:
                default:{
                    break;
                }
            }
        }
        return obj;
    }

}
