package www.bwsensing.com.common.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author macos-zyj
 */
public class AsciiDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) {
        byte[] b = byteBuf.toString(StandardCharsets.US_ASCII).getBytes();
        String asciiString = byteBuf.toString(StandardCharsets.US_ASCII);
        byteBuf.readBytes(b);
        out.add(asciiString);
    }
}
