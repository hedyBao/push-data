package com.baobao.nettyWebSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


public class NettyServer {
    public static final int port = 12346;

    public static void main(String[] args) throws Exception{


        start();
//        new MyScheduler();
//        new MyAutoPush();
    }

    public static void start() throws Exception{
        // Boss线程：由这个线程池提供的线程是boss种类的，用于创建、连接、绑定socket，
        // （有点像门卫）然后把这些socket传给worker线程池。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker线程：Worker线程执行所有的异步I/O，即处理操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
        // ServerBootstrap 启动NIO服务的辅助启动类,负责初始话netty服务器，并且开始监听端口的socket请求
            ServerBootstrap sb = new ServerBootstrap();
        //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
        //用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception{
                            System.out.println("收到新连接");
                            ch.pipeline().addLast(new HttpServerCodec());
                            //以块的方式来写的处理器
                            //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            ch.pipeline().addLast(new HttpObjectAggregator(8192) );
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", "WebSocket", true, 65536 * 10));
                            ch.pipeline().addLast(new TimerHandler());
                            ch.pipeline().addLast(new MyWebSocketHandler());

                        }
                    });
            sb.bind(port).addListener(future->{
                if (future.isSuccess()) {
                    System.out.println(NettyServer.class + " 启动正在监听： "+ port );

                } else {
                    System.out.println(NettyServer.class + " 启动监听失败");
                }
            });

        }catch (Exception e){
            System.out.println(e.getMessage());

        }

    }
}
