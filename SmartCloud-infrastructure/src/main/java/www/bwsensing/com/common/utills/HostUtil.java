package www.bwsensing.com.common.utills;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
public class HostUtil {
    /**
     * 获取主机名称
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    /**
     * 获取系统首选IP
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 获取所有网卡IP，排除回文地址、虚拟地址
     *
     * @return
     * @throws SocketException
     */
    public static String[] getIps() throws SocketException {
        List<String> list = new ArrayList<>();
        Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
        while (enumeration.hasMoreElements()) {
            NetworkInterface anInterface = enumeration.nextElement();
            if (anInterface.isLoopback() || anInterface.isVirtual()) {
                continue;
            }
            Enumeration<InetAddress> inets = anInterface.getInetAddresses();
            while (inets.hasMoreElements()) {
                InetAddress address = inets.nextElement();
                if (address.isLoopbackAddress() || !address.isSiteLocalAddress() || address.isAnyLocalAddress()) {
                    continue;
                }
                list.add(address.getHostAddress());
            }
        }
        return list.toArray(new String[0]);
    }

}
