package com.baobao.nettyWebSocket;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;
//通道组池，管理所有websocket连接
public class MyChannelHandlerPool {
    public MyChannelHandlerPool(){}
    public static Map<String , ChannelId> channelIdMap = new HashMap<>();
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
