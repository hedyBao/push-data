package com.baobao.nettyWebSocket;

import com.alibaba.fastjson.JSON;
import com.baobao.order.PushContextBo;
import com.baobao.order.TmpBo;
import com.baobao.syn.trans.SOrder;
import com.baobao.syn.trans.SynToPushCtx;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MyAutoPush {
    static {
        TmpBo tmpBo = SOrder.newSynOrder();
        if(SOrder.isNewSynOrder(tmpBo)){
            PushContextBo p2 = SynToPushCtx.synToPushCtx(tmpBo);
            String pushText = JSON.toJSONString(p2);
            MyChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(pushText));

        }
    }
}
