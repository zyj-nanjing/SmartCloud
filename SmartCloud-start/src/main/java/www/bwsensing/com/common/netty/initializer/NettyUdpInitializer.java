package www.bwsensing.com.common.netty.initializer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.netty.handler.NettyUdpServerHandler;

/**
 * @author macos-zyj
 */
@Component
public class NettyUdpInitializer extends ChannelInitializer<Channel> {
    @Autowired
    private NettyUdpServerHandler nettyUdpServer;
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(nettyUdpServer);
    }
}
