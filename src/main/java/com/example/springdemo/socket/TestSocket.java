/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author xuleyan
 * @version TestSocket.java, v 0.1 2019-08-19 7:42 AM xuleyan
 */
public class TestSocket {

    public static void main(String[] args) throws IOException {
        // 监听端口
        ServerSocket serverSocket = new ServerSocket(8033);

        while (true) {
            // 接受请求，建立连接
            Socket socket = serverSocket.accept();

            // 数据交换
            // new Thread(new BIOServerHandler(socket)).start();
        }

        // serverSocket.close();

    }

    public void testChannel() throws IOException {
        // 通过服务器端socket创建channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 服务端绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8033));

        // 服务器端监听客户端连接，建立socketChannel连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 客户端连接远程主机及端口
        SocketChannel socketChannel1 = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8033));
    }
}