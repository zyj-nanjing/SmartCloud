package www.bwsensing.com.common.scheduler;

import java.net.SocketException;
import java.util.Date;
import java.util.List;

import com.alibaba.cola.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.annotation.BizScheduled;
import www.bwsensing.com.common.utills.NetworkInterfaceUtil;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.gatewayimpl.database.BizScheduledMapper;
import www.bwsensing.com.gatewayimpl.database.ScheduledExecuteMapper;
import www.bwsensing.com.gatewayimpl.database.ServiceDeployMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.BizScheduledConfig;
import www.bwsensing.com.gatewayimpl.database.dataobject.ScheduledExecute;
import www.bwsensing.com.gatewayimpl.database.dataobject.ServiceDeploy;


/**
 * @author macos-zyj
 */
@Component
@Aspect
@Slf4j
public class BizScheduledAspect {
    private static final Integer DEFAULT_CHECK_INTERVAL = 500;

    @Resource
    private ServiceDeployMapper serviceDeployMapper;

    @Resource
    private BizScheduledMapper scheduledMapper;

    @Resource
    private ScheduledExecuteMapper scheduledExecuteMapper;


    @Pointcut("@annotation(www.bwsensing.com.common.annotation.BizScheduled)")
    private void pointcut() {
    }

    /**
     * 此处需要加事务 若存在执行一半系统挂了的情况可以保障后续的机器执行时该系列操作不会影响到后面的操作
     * 默认为优先级最高的先执行 后续服务默认按照优先级及偏移量暂停 暂停结束后 检测该任务是否执行完成
     * 若完成则不执行。 若未完成则需要设置前面所有节点的健康状态并且重新计算偏移量 减少下一次执行所带来的停顿
     * 若高优先级节点恢复则重新计算偏移量并改变状态 但是为了保证状态不乱则当前不执行 由后续节点先执行
     * @param pjp
     * @param scheduled
     * @return
     * @throws InterruptedException
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,rollbackFor=Exception.class)
    @Around(value = "pointcut() && @annotation(scheduled)", argNames = "pjp,scheduled")
    public Object  aroundPointcut(ProceedingJoinPoint pjp, BizScheduled scheduled) throws InterruptedException, SocketException {
        String ipAddress = NetworkInterfaceUtil.getIp4Addresses().get(1);
        String hostname = NetworkInterfaceUtil.getHostName();
        log.info("---- 开始执行分布式多机定时事务, 定时事务名称:{},   Ip地址:{},   主机名:{}", scheduled.scheduledName(),ipAddress,hostname);
        ServiceDeploy deploy = getCurrentServiceDeploy(ipAddress,hostname);
        BizScheduledConfig scheduledConfig;
        Object obj;
        if (deploy.getIsHealthy()){
            scheduledConfig = checkAndGetConfig(deploy,scheduled);
            try{
                if (null != scheduledConfig){
                    obj = pjp.proceed();
                    endExecute(scheduledConfig,deploy.getId());
                    log.info("---- 分布式多机定时事务结束, 定时事务名称:{},   地址:{}", scheduled.scheduledName(), NetworkInterfaceUtil.getHostIp());
                    return obj;
                }

            } catch (Throwable throwable) {
                obj=throwable.toString();
                return obj;
            }
            return null;
        } else {
            updateDeploy(scheduled,deploy);
            log.info("---- 分布式多机定时事务 重新激活, 定时事务名称:{},   地址:{}", scheduled.scheduledName(), NetworkInterfaceUtil.getHostIp());
            throw new BizException("SERVICE_FIRST_ONLINE","部署节点刚恢复暂不执行定时任务!");
        }
    }

    private BizScheduledConfig checkAndGetConfig(ServiceDeploy deploy,BizScheduled scheduled) throws InterruptedException {
        BizScheduledConfig scheduler = getSchedulerConfig(scheduled.scheduleCode(),deploy.getId());
        if ( null == scheduler){
            throw new BizException("SCHEDULER_NOT_DIST","定时任务未分配,请勿未经配置使用该注解");
        } else {
            double interval = scheduler.getCheckInterval() * (scheduler.getWeight()-scheduler.getShiftWeight());
            if( interval > 0){
                Thread.sleep((int) interval);
                if (!checkExecuted(scheduler)){
                    offlineDeploy(scheduler);
                } else {
                   return null;
                }
            }
            setScheduledExecute(scheduler,deploy);
        }
        return scheduler;
    }
    /**
     * 判断前面的定时任务是否执行完成
     * @return
     */
    private Boolean checkExecuted(BizScheduledConfig scheduler){
        List<ScheduledExecute> executes = scheduledExecuteMapper.queryScheduledExecute(new ScheduledExecute(scheduler.getId(),DEFAULT_CHECK_INTERVAL));
        if(null != executes && executes.size() >0){
            return executes.get(0).getIsEnd();
        }
        return false;
    }

    private void  endExecute(BizScheduledConfig scheduler,Integer serviceId){
        List<ScheduledExecute> executes = scheduledExecuteMapper.queryScheduledExecute(new ScheduledExecute(scheduler.getId(),DEFAULT_CHECK_INTERVAL));
        if(null != executes && executes.size() >0){
            ScheduledExecute execute =  executes.get(0);
            if(execute.getServiceId().equals(serviceId)){
                execute.setEndTime(new Date());
                execute.setIsEnd(true);
                scheduledExecuteMapper.updateScheduledExecute(execute);
            } else {
                throw new SysException("SCHEDULE_EXECUTE_CONFLICT");
            }
        }
    }

    private void setScheduledExecute(BizScheduledConfig scheduler,ServiceDeploy deploy){
        ScheduledExecute execute = new ScheduledExecute();
        execute.setScheduleId(scheduler.getId());
        execute.setServiceId(deploy.getId());
        scheduledExecuteMapper.saveScheduledExecute(execute);
    }

    private ServiceDeploy getCurrentServiceDeploy(String ipAddress,String hostname) {
        List<ServiceDeploy> deploys = serviceDeployMapper.selectServiceDeployList(new ServiceDeploy(hostname,ipAddress));
        if (deploys.size()>0){
            return deploys.get(0);
        }
        throw new BizException("Current service not register");
    }

    private BizScheduledConfig getSchedulerConfig(String code,Integer serviceId){
        return scheduledMapper.queryScheduleByCode(code,serviceId);
    }

    /**
     * 修改设备状态 重新计算偏移量
     * @param scheduled
     * @param deploy
     */
    private void updateDeploy(BizScheduled scheduled,ServiceDeploy deploy){
        deploy.setIsHealthy(true);
        serviceDeployMapper.updateServiceDeploy(deploy);
        BizScheduledConfig scheduler = getSchedulerConfig(scheduled.scheduleCode(),deploy.getId());
        if (null != scheduler) {
            countScheduleReleaseShift(scheduler.getWeight(),scheduler.getId());
        }
    }

    /**
     * 前置执行节点存在未执行情况的操作
     * 将前置节点状态修改为不健康 并开始计算偏移量从而降低等待时间
     * @param scheduler
     */
    private void  offlineDeploy(BizScheduledConfig scheduler){
        //执行到此说明前面的服务器可能以及宕机了 需要改变服务器执行状态
        List<ServiceDeploy> deploys = serviceDeployMapper.selectServiceDeployByWeight(scheduler.getWeight(),scheduler.getId());
        if (null != deploys && deploys.size() > 0 ){
            for (ServiceDeploy deploy : deploys){
                deploy.setIsHealthy(false);
                serviceDeployMapper.updateServiceDeploy(deploy);
            }
        }
        countScheduleReleaseShift(scheduler.getWeight(),scheduler.getId());
    }

    /**
     * 计算偏移量
     * @param weight
     * @param id
     */
    private void countScheduleReleaseShift(Double weight, Integer id){
        List<BizScheduledConfig> schedules = scheduledMapper.queryScheduleByWeight(weight,id);
        double minWeight = weight;
        if (null != schedules && schedules.size() > 0 ){
            minWeight = schedules.get(0).getWeight();
            for (BizScheduledConfig schedule : schedules){
                if(schedule.getWeight()<minWeight){
                    minWeight = schedule.getWeight();
                }
            }
        }
        double intervalWeight = weight - minWeight;
        scheduledMapper.updateScheduleReleaseShift(intervalWeight,weight,id);
    }
}

