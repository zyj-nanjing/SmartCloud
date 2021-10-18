package www.bwsensing.com.common.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.bwsensing.com.common.utills.AddressUtils;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.device.data.MonitorReceive;
import www.bwsensing.com.domain.gateway.MonitorReceiveGateway;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.project.analyse.SensorDataContext;
import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 朱永杰
 */
@Slf4j
@ChannelHandler.Sharable
@Service("nettyServerHandler")
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /** 空闲次数 */
    private AtomicInteger idleCount = new AtomicInteger(1);

    private final ConcurrentHashMap<String,String> ipChannelCache = new ConcurrentHashMap<>();


    private  ConcurrentHashMap<String,String> ipCache = new ConcurrentHashMap<>();

    @Autowired
    private MonitorReceiveGateway sensorDataGateway;

    @Resource
    private SensorDataContext sensorDataContext;

    /**
     * 建立连接时，发送一条消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接的客户端地址：{},客户端Id:{}",ctx.channel().remoteAddress(),ctx.channel().id().asShortText());
        ipChannelCache.put(ctx.channel().remoteAddress().toString(),ctx.channel().id().asShortText());
        ipCache.put(ctx.channel().remoteAddress().toString(),"");
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
        try {
            String rawText = msg.toString();
            if (StringUtils.isNotEmpty(rawText)){
                String ipAddress = ctx.channel().remoteAddress().toString();
                if (StringUtils.isEmpty(ipCache.get(ipAddress))){
                    ipCache.put(ipAddress,rawText);
                } else {
                    String currentValue = ipCache.get(ipAddress)+"\n";
                    ipCache.put(ipAddress,currentValue+rawText);
                }
                log.debug("Data receive Client address:{}，Raw message:{}", ipAddress,msg);
            }
        } catch (Exception e) {
            log.warn("netty channput(ctx.channel().remoteAddress().toString(),ctx.channel().id().asShortText());elRead error Message:{}",e.getLocalizedMessage());
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void setMonitorReceiveData(MonitorReceive receive,String ipAddress,String channel,Date receiveTime){
        String fixIp = ipAddress.substring(1, ipAddress.indexOf(":"));
        receive.setIp(fixIp);
        receive.setChannelId(channel);
        receive.setReceiveTime(receiveTime);
        receive.setSendAddress(AddressUtils.getRealAddressByIp(fixIp));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        //客户端断开后批量保存
        String channelId = ctx.channel().id().asShortText();
        String ipAddress = ctx.channel().remoteAddress().toString();
        String totalReceive = ipCache.get(ipAddress);
        if (StringUtils.isNotEmpty(totalReceive)){
            Date receiveTime = new Date();
            MonitorReceive monitorReceive = sensorDataContext.analyseRawData(totalReceive);
            setMonitorReceiveData(monitorReceive,ipAddress,channelId,receiveTime);
            sensorDataGateway.storageMonitorReceive(monitorReceive);
            log.info("start to analyse and storage received data SensorSn:{}",monitorReceive.getSn());
        }
        ipCache.remove(ipAddress);
        log.info("link down ,Client address:{}",ctx.channel().remoteAddress());
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
