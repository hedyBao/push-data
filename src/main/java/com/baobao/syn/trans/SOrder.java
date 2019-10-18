package com.baobao.syn.trans;

import com.baobao.order.TmpBo;

import java.util.Random;

public class SOrder {
    public static Boolean isNewSynOrder(TmpBo tmpBo){
        if(tmpBo.getTime()!=System.currentTimeMillis()){
            return  true;
        }
        return false;
    }

    public static TmpBo newSynOrder(){
        TmpBo tmpBo = new TmpBo();
        tmpBo.setOrderId(new Random().nextInt());
        tmpBo.setTime(System.currentTimeMillis());
        return tmpBo;

    }
}
