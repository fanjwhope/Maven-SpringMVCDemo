package com.myDemo.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * ip工具类
 */
public class IpUtil {
   private static Integer IP_LENGTH = 15;
   private static String IP_SPE = ",";
   private static String UNKNOWN = "unknown";

    /**
     * 获取访问人的ip
     *
     * @param request 请求
     * @return IP Address
     */
   public static String getIpAddrByRequest(HttpServletRequest request) {
        String remoteIp;
        remoteIp = request.getHeader("x-forwarded-for");
        if (remoteIp == null || remoteIp.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("HTTP_X_FORWARDED-FOR");
        }
        if (remoteIp == null || remoteIp.length() == 0 || UNKNOWN.equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getRemoteAddr();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if (remoteIp != null && remoteIp.length() > IP_LENGTH) {
            if (remoteIp.indexOf(IP_SPE) > 0) {
                remoteIp = remoteIp.substring(0, remoteIp.indexOf(","));
            }
        }
        return remoteIp;
    }

    /**
     * 获取本机ip地址
     *
     * @return 本机IPSocketException
     * @throws SocketException ..
     */
    public static String getRealIp() throws SocketException {
        // 本地IP，如果没有配置外网IP则返回它
        String localip = null;
        // 外网IP
        String netip = null;

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        // 是否找到外网IP
        boolean finded = false;
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                // 外网IP
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                    // 内网IP
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                    localip = ip.getHostAddress();
                }
            }
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }
}
