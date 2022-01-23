package www.bwsensing.com.common.netty;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.logging.LogLevel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.stereotype.Service;
import io.netty.handler.logging.LoggingHandler;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import www.bwsensing.com.common.constant.NettyConstants;
import www.bwsensing.com.common.netty.initializer.NettyTcpInitializer;

/**
 * @author 朱永杰
 */
@Slf4j
@Service("nettyTcpServer")
public class NettyTcpServer {

    @Resource
    private NettyTcpInitializer nettyServerInitializer;


    public void run() {
        log.info("Tcp service init!");
        EventLoopGroup parentGroup = new NioEventLoopGroup();;
        EventLoopGroup childGroup = new NioEventLoopGroup();;
        ServerBootstrap httpBootstrap = new ServerBootstrap();;
        try {
            httpBootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(nettyServerInitializer);
            // 服务器绑定端口监听
            ChannelFuture channelHttp = httpBootstrap.bind(NettyConstants.TCP_PORT).sync();
            log.info("TCP服务监听端口启动成功，端口为:{}", NettyConstants.TCP_PORT);
            channelHttp.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("TCP Service start error cause:{}",e.getMessage());
        } finally {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}
