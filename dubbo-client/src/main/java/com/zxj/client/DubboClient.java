package com.zxj.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by zhang4838223 on 2015/10/24.
 */
public class DubboClient {
    private static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"client.xml"});
    public static void main(String[] args) {
        ctx.start();
        System.out.println("dubbo client is started!!");
        try {
            System.in.read(); // 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ClassPathXmlApplicationContext getContext(){
//        ctx.start();
        return ctx;
    }
}
