package www.bwsensing.com.common.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author 朱永杰
 */
public class HexDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
        //创建字节数组,buffer.readableBytes可读字节长度
        byte[] b = new byte[buffer.readableBytes()];
        //复制内容到字节数组b
        buffer.readBytes(b);
        //字节数组转字符串
        out.add(bytesToHexString(b));
    }

    public String bytesToHexString(byte[] bArray) {
        StringBuilder stringBuilder = new StringBuilder(bArray.length);
        String sTemp;
        for (byte b : bArray) {
            sTemp = Integer.toHexString(0xFF & b);
            if (sTemp.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(sTemp.toUpperCase());
        }
        return stringBuilder.toString();
    }

    public static String toHexString(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (byte value : b) {
            buffer.append(toHexString(value));
        }
        return buffer.toString();
    }

    public static String toHexString(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }
}
