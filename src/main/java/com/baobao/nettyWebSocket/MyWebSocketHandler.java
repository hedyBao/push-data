package com.baobao.nettyWebSocket;
import com.alibaba.fastjson.JSON;
import com.baobao.order.TmpBo;
import com.baobao.syn.trans.SOrder;
import com.baobao.syn.trans.SynToPushCtx;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！");
        //添加到channelGroup通道组
        MyChannelHandlerPool.channelGroup.add(ctx.channel());
//         final ByteBuf time = ctx.alloc().buffer(4); // (2)
//        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

//        ctx.channel().writeAndFlush(new TextWebSocketFrame("1")); // (3)
//        f.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) {
//                assert f == future;
//                ctx.close();
//            }
//        });

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端断开连接，通道关闭！");
        //添加到channelGroup 通道组
        MyChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {
        //正常的TEXT消息类型
        TextWebSocketFrame frame = (TextWebSocketFrame)msg;
        System.out.println("服务器收到的数据是：" +frame.text());
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("123"));
    }


}
