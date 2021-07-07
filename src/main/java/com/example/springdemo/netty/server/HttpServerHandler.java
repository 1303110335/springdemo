/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.example.springdemo.netty.server;

//import com.aliyun.openservices.shade.io.netty.handler.codec.http.DefaultFullHttpResponse;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * 自定义服务端处理器
 * 需求：用户提交一个请求后，在浏览器上就会看到hello netty world
 *
 * @author xuleyan
 * @version HttpServerHandler.java, v 0.1 2020-04-07 5:45 PM xuleyan
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当channel中有来自于客户端的数据时就会触发该方法的执行
     *
     * @param ctx 上下文对象
     * @param msg 就是来自于客户端的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("msg = " + msg);
//        System.out.println("客户端地址 = " + ctx.channel().remoteAddress());

        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            System.out.println("请求方式：" + request.method().name());
            System.out.println("请求URI：" + request.uri());

            if ("/favicon.ico".equals(request.uri())) {
                System.out.println("不处理/favicon.ico请求");
                return;
            }

            // 构造response的响应体
            ByteBuf body = Unpooled.copiedBuffer(" hello netty world", CharsetUtil.UTF_8);
            // 生成响应体
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, body);
            // 获取到的response的头部进行转化
            HttpHeaders headers = response.headers();
            headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            headers.set(HttpHeaderNames.CONTENT_LENGTH, body.readableBytes());

            // 将响应对象写入channel
//            ctx.write(response);
//            ctx.flush();
            ctx.writeAndFlush(response)
                    // 添加监听，响应体发送完毕则直接将channel关闭
                    .addListener(ChannelFutureListener.CLOSE);

        }
    }

    /**
     * 当channel中的数据在处理过程中出现异常时会触发该方法的执行
     *
     * @param ctx   上下文
     * @param cause 发生的异常对象
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 关闭channel
        ctx.close();
    }
}