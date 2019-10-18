package com.baobao.syn.trans;

import com.baobao.order.PushContextBo;
import com.baobao.order.TmpBo;

public class SynToPushCtx {

    public static PushContextBo synToPushCtx(TmpBo tmpBo){

        PushContextBo p1 = new PushContextBo();

        if(tmpBo!=null){
            p1.setOrderId(tmpBo.getOrderId());
            p1.setTime(tmpBo.getTime());
        }
        return p1;
    }


}
