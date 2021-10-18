package www.bwsensing.com.common.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author macos-zyj
 */
public class NettyServerDecoder extends ByteToMessageDecoder {
    private final Integer localPort;

    public NettyServerDecoder(Integer localPort) {
        this.localPort = localPort;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        String analyseRaw = in.toString(StandardCharsets.US_ASCII);
        String[] dataArray = analyseRaw.split(",");
        if (dataArray.length >1){
            new AsciiDecoder().decode(ctx,in,out);
        } else {
            new HexDecoder().decode(ctx, in, out);
        }
    }


}
