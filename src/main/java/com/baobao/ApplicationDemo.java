package com.baobao;

import com.baobao.nettyWebSocket.NettyServer;
import org.springframework.boot.SpringApplication;

public class ApplicationDemo {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationDemo.class,args);
        try{
            System.out.println("http://127.0.0.1:6688/netty-websocket/index");
            new NettyServer().start();
        }catch (Exception e){
            System.out.println("NettyServerError:"+e.getMessage());
        }

    }

}
