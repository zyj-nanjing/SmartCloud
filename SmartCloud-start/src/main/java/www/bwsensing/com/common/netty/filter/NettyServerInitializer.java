package www.bwsensing.com.common.netty.filter;

import www.bwsensing.com.common.constant.NettyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.common.netty.decoder.NettyServerDecoder;
import www.bwsensing.com.common.netty.handler.NettyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @author macos-zyj
 */
@Component
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private NettyServerHandler nettyServerHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        int localPort = socketChannel.localAddress().getPort();
        pipeline.addLast(new IdleStateHandler(
                NettyConstants.SERVER_READ_IDEL_TIME_OUT,
                NettyConstants.SERVER_WRITE_IDEL_TIME_OUT,
                NettyConstants.SERVER_ALL_IDEL_TIME_OUT,
                TimeUnit.SECONDS))
        .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
        .addLast(new StringEncoder())
        .addLast(new ByteArrayEncoder())
        //根据端口动态的选择解码器
        .addLast("decoder",new NettyServerDecoder(localPort))
        .addLast("nettyServerHandler", nettyServerHandler);
    }
}
