/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xuleyan
 * @version SomeServer.java, v 0.1 2020-04-07 6:53 AM xuleyan
 */
public class SomeServer {

    public static void main(String[] args) throws InterruptedException {

        // 用于处理客户端连接请求，将请求发送给childGroup中的eventLoop
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        // 用于处理客户端请求
        EventLoopGroup childGroup = new NioEventLoopGroup();


        try {
            // 用于启动ServerChannel
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 指定eventLoopGroup
            bootstrap.group(parentGroup, childGroup)
                    // 使用nio进行通信
                    .channel(NioServerSocketChannel.class)
                    // 指定childGroup中的eventLopp所绑定的线程所需要的处理器
                    .childHandler(new SomeChannelInitializer());

            // 绑定端口号
            // bind()方法的执行是异步的
            ChannelFuture future = bootstrap.bind(8888).sync();
            System.out.println("服务器启动成功：监听端口为:8888");
            // 关闭Channel
            // closeFuture()的执行是异步的
            // sync方法会使绑定操作与后续的代码的执行由异步变为同步
            future.channel().closeFuture().sync();
        } finally {
            // 优雅关闭
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}