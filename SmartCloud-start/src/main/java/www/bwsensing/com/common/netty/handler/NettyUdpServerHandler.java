package www.bwsensing.com.common.netty.handler;

import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import javax.annotation.Resource;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.springframework.stereotype.Service;
import www.bwsensing.com.domainevent.object.DataMessage;
import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.thread.NamedThreadFactory;
import www.bwsensing.com.common.utills.StringUtils;
import io.netty.channel.SimpleChannelInboundHandler;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import www.bwsensing.com.domainevent.FacilityDataReceiveEvent;

/**
 * @author macos-zyj
 */
@Slf4j
@ChannelHandler.Sharable
@Service("nettyUdpServerHandler")
public class NettyUdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static final Long WAIT_TIMEOUT = 30000L;
    private ExecutorService nettyUdpThread = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1000),
            new NamedThreadFactory("ASYNC-UDP-CACHE-POOL"));

    public static NettyUdpServerHandler nettyUdpServerHandler;

    private static final ConcurrentHashMap<String, List<DataMessage>> IP_DATA_CACHE = new ConcurrentHashMap<>();

    @Resource
    private DomainEventPublisher domainEventPublisher;

    @Resource
    private RedisService redisService;


    public NettyUdpServerHandler(){

    }

    @PostConstruct
    public void init(){
        nettyUdpServerHandler = this;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        InetSocketAddress  remoteAddress = msg.sender();
        String address = remoteAddress.getAddress().getHostAddress();
        String message = msg.content().toString(CharsetUtil.UTF_8);
        if (StringUtils.isNotEmpty(message)){
            storageAndWait(address,message);
        }
    }

    private  void storageAndWait(String ipAddress,String receiveMessage){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        if (redisService.hasKey(ipAddress)&& null != IP_DATA_CACHE.get(ipAddress)){
            IP_DATA_CACHE.get(ipAddress).add(new DataMessage(timestamp,receiveMessage));
            redisService.setCacheObject(ipAddress,receiveMessage,WAIT_TIMEOUT,TimeUnit.MILLISECONDS);
        } else {
            List<DataMessage> messages = new ArrayList<>();
            messages.add(new DataMessage(timestamp,receiveMessage));
            IP_DATA_CACHE.put(ipAddress,messages);
            redisService.setCacheObject(ipAddress,receiveMessage,WAIT_TIMEOUT,TimeUnit.MILLISECONDS);
            commitThreadWaitBatch(ipAddress);
        }
    }

    private void commitThreadWaitBatch(String ipAddress){
        nettyUdpThread.submit(() -> {
            while(true){
                if(!redisService.hasKey(ipAddress)){
                    if (IP_DATA_CACHE.get(ipAddress).size()>0){
                        Date receiveTime = new Date();
                        FacilityDataReceiveEvent receiveEvent = new FacilityDataReceiveEvent();
                        receiveEvent.setReceiveData(IP_DATA_CACHE.get(ipAddress));
                        setReceiveEvent(receiveEvent,ipAddress,receiveTime);
                        domainEventPublisher.transactionPublish(receiveEvent);
                        log.info("facility data pushed,Client address:{}",ipAddress);
                    }
                    IP_DATA_CACHE.remove(ipAddress);
                    log.info("link down ,Client address:{}",ipAddress);
                    break;
                } else {
                    try {
                        Thread.sleep(WAIT_TIMEOUT/4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setReceiveEvent(FacilityDataReceiveEvent receive,String ipAddress,Date receiveTime){
        receive.setChannelId("UDP");
        receive.setIp(ipAddress);
        receive.setReceiveTime(receiveTime);
    }
}
