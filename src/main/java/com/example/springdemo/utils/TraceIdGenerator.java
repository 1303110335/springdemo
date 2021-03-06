/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.utils;

import com.fshows.fsframework.core.constants.StringPool;
import com.fshows.fsframework.core.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuleyan
 * @version TraceIdGenerator.java, v 0.1 2019-04-17 8:39 AM xuleyan
 */
@Slf4j
public class TraceIdGenerator {
    private static String IP_16 = "ffffffff";
    private static AtomicInteger count = new AtomicInteger(1000);
    private static String P_ID_CACHE = null;

    static {
        String ipAddress = getInetAddress();
        if (ipAddress != null) {
            IP_16 = getip16(ipAddress);
        }
    }

    public static String generate() {
        return getTraceId(IP_16, System.currentTimeMillis(), getNextId());
    }

    private static String getTraceId(String ip, long timestamp, int nextId) {
        StringBuilder builder = new StringBuilder(30);
        builder.append(ip).append(timestamp).append(nextId).append(getPID());
        return builder.toString();
    }

    private static int getNextId() {
        for (; ; ) {
            int current = count.get();
            int next = (current > 9000) ? 1000 : current + 1;
            if (count.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    private static String getip16(String ip) {
        String[] ips = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String column : ips) {
            String hex = Integer.toHexString(Integer.parseInt(column));
            if (hex.length() == 1) {
                sb.append("0").append(hex);
            } else {
                sb.append(hex);
            }
        }
        return sb.toString();
    }

    private static String getInetAddress() {
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress address = null;
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && address.getHostAddress().indexOf(':') == -1) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e ) {
            LogUtil.error(log, "??????IP??????SocketException??????,e={}", e);
        }catch (NullPointerException e){
            LogUtil.error(log, "??????IP??????NullPointerException??????,e={}", e);
        }
        return null;

    }

    /**
     * ???????????? JDK9 ?????????????????????????????????????????????????????? JDK ?????????????????????????????????????????????
     * ??? Mac ????????????JDK6???JDK7???JDK8 ??????????????????
     * ??? Linux ????????????JDK6???JDK7???JDK8 ?????????????????????????????????
     *
     * @return ?????? ID
     */
    private static String getPID() {
       if (P_ID_CACHE != null) {
           return P_ID_CACHE;
       }
       String processName = ManagementFactory.getRuntimeMXBean().getName();

       if (StringUtils.isBlank(processName)) {
           return StringPool.EMPTY;
       }

       String[] processSplitName = processName.split(StringPool.AT);

       if (processSplitName.length == 0) {
           return StringPool.EMPTY;
       }

       String pid = processSplitName[0];
       P_ID_CACHE = pid;
       return pid;
    }

    public static void main(String[] args) {
//        System.out.println(getPID());
//        System.out.println(getNextId());
        System.out.println(generate());
    }
}