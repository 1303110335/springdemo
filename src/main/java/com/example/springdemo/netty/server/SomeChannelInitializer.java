/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * 管道初始化器
 * 再初始化后就会被gc
 *
 * @author xuleyan
 * @version SomeChannelInitializer.java, v 0.1 2020-04-07 7:58 AM xuleyan
 */
public class SomeChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 从channel中获取pipeline
        ChannelPipeline pipeline = ch.pipeline();
        // 将HttpServerCodec处理器放入到pipeline的最后
        // HttpServerCodec是HttpRequestDecoder与HttpResponseEncoder的复合体
        // HttpRequestDecoder http请求解码器，将channel中的byteBuffer数据解码为HttpRequest对象
        // HttpResponseEncoder: http响应编码器，将HttpResponse对象编码为将要在channel中发送的ByteBuffer数据
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 再将自定义的处理器加到最后
        pipeline.addLast("httpServerHandler", new HttpServerHandler());
    }
}