package com.baobao.nettyWebSocket;


import com.alibaba.fastjson.JSON;
import com.baobao.order.PushContextBo;
import com.baobao.order.TmpBo;
import com.baobao.syn.trans.SynToPushCtx;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MyScheduler {

    static{
//        这里的意思是定时向前端推送不一样的订单，但我目前应该构思的是只要有新订单就立即推送
        ScheduledExecutorService executor1 = Executors.newSingleThreadScheduledExecutor();
        executor1.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    TmpBo tmpBo = new TmpBo();
                    tmpBo.setOrderId(new Random().nextInt());
                    tmpBo.setTime(System.currentTimeMillis());
                    PushContextBo p2 = SynToPushCtx.synToPushCtx(tmpBo);
                    String pushText = JSON.toJSONString(p2);
                    MyChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(pushText));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }, 0, 3, TimeUnit.SECONDS);
        MyChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame("19923"));


//            每隔5s向前端发送数据
//            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//            executor.scheduleAtFixedRate(()-> MyChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame("123))
//
//                    ,0,5000, TimeUnit.MILLISECONDS);




    }
}
