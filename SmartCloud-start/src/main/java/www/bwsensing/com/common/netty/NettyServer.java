package www.bwsensing.com.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import www.bwsensing.com.common.constant.NettyConstants;
import www.bwsensing.com.common.netty.filter.NettyServerInitializer;

import javax.annotation.Resource;

/**
 * @author 朱永杰
 */
@Slf4j
@Service("nettyHttpServer")
public class NettyServer {

    @Resource
    private NettyServerInitializer nettyServerInitializer;


    public void run() {
        EventLoopGroup parentGroup = new NioEventLoopGroup();;
        EventLoopGroup childGroup = new NioEventLoopGroup();;
        ServerBootstrap serverBootstrap = new ServerBootstrap();;
        try {
            serverBootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(nettyServerInitializer);
            // 服务器绑定端口监听
            ChannelFuture channelHttp = serverBootstrap.bind(NettyConstants.HTTP_PORT).sync();
            log.info("HTTP服务监听端口启动成功，端口为:{}", NettyConstants.HTTP_PORT);
            channelHttp.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}
