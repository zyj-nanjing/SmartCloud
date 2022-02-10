package www.bwsensing.com.common.scheduler;

import java.util.List;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.common.annotation.BizScheduled;
import www.bwsensing.com.common.utills.NetworkInterfaceUtil;
import www.bwsensing.com.gatewayimpl.database.BizScheduledMapper;
import www.bwsensing.com.gatewayimpl.database.ServiceDeployMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.BizScheduledConfig;
import www.bwsensing.com.gatewayimpl.database.dataobject.ServiceDeploy;
import javax.annotation.Resource;


/**
 * @author macos-zyj
 */
@Component
@Aspect
@Slf4j
public class BizScheduledAspect {

    @Resource
    private ServiceDeployMapper serviceDeployMapper;

    @Resource
    private BizScheduledMapper scheduledMapper;

    @Pointcut("@annotation(www.bwsensing.com.common.annotation.BizScheduled)")
    private void pointcut() {
    }

    @Before("pointcut() && @annotation(scheduled)")
    public void beforePointcut(BizScheduled scheduled) throws InterruptedException {
        String ipAddress = NetworkInterfaceUtil.getHostIp();
        String hostname = NetworkInterfaceUtil.getHostName();
        log.info("---- 开始执行分布式多机定时事务, 定时事务名称:{},   Ip地址:{},   主机名:{}", scheduled.scheduledName(),ipAddress,hostname);
        ServiceDeploy deploy = getCurrentServiceDeploy(ipAddress,hostname);
        if (deploy.getIsHealthy()){
            BizScheduledConfig scheduler = getSchedulerConfig(scheduled.scheduleCode(),deploy.getId());
            if ( null == scheduler){
                throw new BizException("SCHEDULER_NOT_DIST","定时任务未分配,请勿未经配置使用该注解");
            } else {
                double interval = scheduler.getCheckInterval() * (scheduler.getWeight()-scheduler.getShiftWeight());
                if( interval > 0){
                    Thread.sleep((int) interval);
                    if (!checkExecuted()){
                        offlineDeploy(scheduler);
                    }
                }
            }
        } else {
            updateDeploy(scheduled,deploy);
            log.info("---- 分布式多机定时事务 重新激活, 定时事务名称:{},   地址:{}", scheduled.scheduledName(), NetworkInterfaceUtil.getHostIp());
            throw new BizException("SERVICE_FIRST_ONLINE","部署节点刚恢复暂不执行定时任务!");
        }
    }

    /**
     * 未完待续 最重要的一步 如何判断前面的定时任务是否执行完成
     * @return
     */
    private Boolean checkExecuted(){
        return false;
    }


    @After("pointcut() && @annotation(scheduled)")
    public void afterPointcut(BizScheduled scheduled) {
        log.info("---- 分布式多机定时事务结束, 定时事务名称:{},   地址:{}", scheduled.scheduledName(), NetworkInterfaceUtil.getHostIp());
    }


    private ServiceDeploy getCurrentServiceDeploy(String ipAddress,String hostname) {
        List<ServiceDeploy> deploys = serviceDeployMapper.selectServiceDeployList(new ServiceDeploy(hostname,ipAddress));
        if (deploys.size()>0){
            return deploys.get(0);
        }
        throw new BizException("Current service not register");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor=RuntimeException.class)
    protected BizScheduledConfig getSchedulerConfig(String code,Integer serviceId){
        return scheduledMapper.queryScheduleByCode(code,serviceId);
    }

    /**
     * 修改设备状态 重新计算偏移量
     * @param scheduled
     * @param deploy
     */
    @Transactional(isolation = Isolation.SERIALIZABLE,rollbackFor=Exception.class)
    protected void updateDeploy(BizScheduled scheduled,ServiceDeploy deploy){
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
    @Transactional(isolation = Isolation.SERIALIZABLE,rollbackFor=Exception.class)
    protected void  offlineDeploy(BizScheduledConfig scheduler){
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

