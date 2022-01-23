package www.bwsensing.com.common.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import www.bwsensing.com.domainevent.FacilityDataReceiveEvent;
import www.bwsensing.com.common.utills.AddressUtils;
import www.bwsensing.com.common.utills.StringUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import io.netty.handler.timeout.IdleStateEvent;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import www.bwsensing.com.domainevent.object.DataMessage;
import javax.annotation.Resource;
import java.util.Date;
/**
 * @author 朱永杰
 */
@Slf4j
@ChannelHandler.Sharable
@Service("nettyTcpServerHandler")
public class NettyTcpServerHandler extends ChannelInboundHandlerAdapter {
    /** 空闲次数 */
    private AtomicInteger idleCount = new AtomicInteger(1);

    private final ConcurrentHashMap<String,String> ipChannelCache = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, List<DataMessage>> IP_CACHE = new ConcurrentHashMap<>();
    @Resource
    private DomainEventPublisher domainEventPublisher;

    /**
     * 建立连接时，发送一条消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接的客户端地址：{},客户端Id:{}",ctx.channel().remoteAddress(),ctx.channel().id().asShortText());
        ipChannelCache.put(ctx.channel().remoteAddress().toString(),ctx.channel().id().asShortText());
        IP_CACHE.put(ctx.channel().remoteAddress().toString(),new ArrayList<>());
        super.channelActive(ctx);
    }

    /**
     * 超时处理 如果5秒没有接受客户端的心跳，就触发; 如果超过两次，则直接关闭;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            // 如果读通道处于空闲状态，说明没有接收到心跳命令
            if (IdleState.READER_IDLE.equals(event.state())) {
                log.info("心跳超时,IP地址:{}",ctx.channel().remoteAddress());
                if (idleCount.get()>1) {
                    log.info("心跳重试后再超时,IP地址:{},关闭通道",ctx.channel().remoteAddress());
                    ctx.channel().close();
                    ipChannelCache.remove(ctx.channel().remoteAddress().toString());
                }
                idleCount.addAndGet(1);
            }
        }
    }

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        try {
            String rawText = msg.toString();
            if (StringUtils.isNotEmpty(rawText)){
                String ipAddress = ctx.channel().remoteAddress().toString();
                if (StringUtils.isEmpty(IP_CACHE.get(ipAddress))){
                    List<DataMessage> messages = new ArrayList<>();
                    messages.add(new DataMessage(timestamp,rawText));
                    IP_CACHE.put(ipAddress,messages);
                } else {
                    IP_CACHE.get(ipAddress).add(new DataMessage(timestamp,rawText));
                }
                log.debug("Data receive Client address:{}，Raw message:{}", ipAddress,msg);
            }
        } catch (Exception e) {
            log.warn("netty channel Read error Message:{}",e.getLocalizedMessage());
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        //客户端断开后批量保存
        String channelId = ctx.channel().id().asShortText();
        String ipAddress = ctx.channel().remoteAddress().toString();
        if (IP_CACHE.get(ipAddress).size()>0){
            Date receiveTime = new Date();
            FacilityDataReceiveEvent receiveEvent = new FacilityDataReceiveEvent();
            receiveEvent.setReceiveData(IP_CACHE.get(ipAddress));
            setReceiveEvent(receiveEvent,ipAddress,channelId,receiveTime);
            domainEventPublisher.transactionPublish(receiveEvent);
            log.info("facility data pushed,Client address:{}",ctx.channel().remoteAddress());
        }
        IP_CACHE.remove(ipAddress);
        log.info("link down ,Client address:{}",ctx.channel().remoteAddress());
    }

    private void setReceiveEvent(FacilityDataReceiveEvent receive,String ipAddress,String channel,Date receiveTime){
        String fixIp = ipAddress.substring(1, ipAddress.indexOf(":"));
        receive.setIp(fixIp);
        receive.setChannelId("TCP");
        receive.setReceiveTime(receiveTime);
        receive.setSendAddress(AddressUtils.getRealAddressByIp(fixIp));
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn("TCP Receive error:{}",cause.getLocalizedMessage());
        ctx.close();
    }
}
