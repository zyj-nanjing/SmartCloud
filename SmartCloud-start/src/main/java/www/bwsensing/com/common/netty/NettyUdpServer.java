package www.bwsensing.com.common.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import www.bwsensing.com.common.constant.NettyConstants;
import www.bwsensing.com.common.netty.initializer.NettyUdpInitializer;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Service("nettyUdpServer")
public class NettyUdpServer {
    @Resource
    private NettyUdpInitializer nettyUdpInitializer;

    public void run() {
        log.info("Udp service init!");
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap udpServer = new Bootstrap();;
        try {
            udpServer.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 100)
                    .handler(nettyUdpInitializer);
            // 服务器绑定端口监听
            ChannelFuture channelHttp = udpServer.bind(NettyConstants.UDP_PORT).sync();
            log.info("UDP服务监听端口启动成功，端口为:{}", NettyConstants.UDP_PORT);
            channelHttp.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("UDP Service start error cause:{}",e.getMessage());
        } finally {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            group.shutdownGracefully();
        }
    }
}
